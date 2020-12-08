package br.com.zup.cartao.proposta.cartao;

import br.com.zup.cartao.proposta.compartilhado.cartao.BloquearCartaoRequest;
import br.com.zup.cartao.proposta.compartilhado.cartao.BloqueioCartaoResultadoResponse;
import br.com.zup.cartao.proposta.compartilhado.cartao.CartaoClient;
import br.com.zup.cartao.proposta.compartilhado.cartao.ResultadoConsultaCartaoResponse;
import br.com.zup.cartao.proposta.proposta.Proposta;
import br.com.zup.cartao.proposta.proposta.PropostaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    private final Logger logger = LoggerFactory.getLogger(CartaoController.class);

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private SolicitacaoClienteBloqueioRepository solicitacaoClienteBloqueioRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @PostMapping("/bloqueio/{numero}")
    @Transactional
    public ResponseEntity<?> bloquearCartao(@Valid @PathVariable("numero") String numeroCartao, HttpServletRequest request) {

        logger.info("iniciando processo de bloqueio do cartão, a pedido do cliente");

        BloqueioCartaoResultadoResponse bloqueioCartaoResultadoResponse =
                cartaoClient.bloquearCartao(numeroCartao, new BloquearCartaoRequest(numeroCartao));

        ResultadoConsultaCartaoResponse resultadoConsultaCartaoResponse =
                cartaoClient.informacoesCartao(numeroCartao);

        Cartao cartao = buildCartao(resultadoConsultaCartaoResponse);

        registraInformacoesCliente(request, numeroCartao);

        Proposta proposta = propostaRepository.findByCartaoNumeroCartao(cartao.getNumeroCartao()).orElseThrow();
        proposta.setCartao(cartao);
        propostaRepository.save(proposta);

        logger.info("cartão bloqueado com sucesso!");

        return ResponseEntity.ok(bloqueioCartaoResultadoResponse);
    }

    public void registraInformacoesCliente(HttpServletRequest request, String numeroCartao) {

        String ip = Optional.ofNullable(request.getHeader("X-FORWARED-FOR")).orElse(request.getRemoteAddr());
        if(ip.equals("0:0:0:0:0:0:0:1")) ip = "127.0.0.1";

        SolicitacaoClienteBloqueio solicitacaoClienteBloqueio = new SolicitacaoClienteBloqueio(
                numeroCartao,
                ip,
                request.getHeader("User-Agent"));

        logger.info("gravando informações do requerente do bloqueio");

        solicitacaoClienteBloqueioRepository
                .save(solicitacaoClienteBloqueio);
    }

    public Cartao buildCartao(ResultadoConsultaCartaoResponse resultadoConsultaCartaoResponse) {
        return new Cartao(resultadoConsultaCartaoResponse.getId(),
                resultadoConsultaCartaoResponse.getEmitidoEm(),
                resultadoConsultaCartaoResponse.getTitular(),
                resultadoConsultaCartaoResponse.getLimite(),
                Long.valueOf(resultadoConsultaCartaoResponse.getIdProposta()))
                .buildBloqueios(resultadoConsultaCartaoResponse.getBloqueios())
                .buildVencimento(resultadoConsultaCartaoResponse.getVencimento())
                .buildRenegociacao(resultadoConsultaCartaoResponse.getRenegociacao())
                .buildParcelas(resultadoConsultaCartaoResponse.getParcelas())
                .buildAvisos(resultadoConsultaCartaoResponse.getAvisos())
                .buildCarteiras(resultadoConsultaCartaoResponse.getCarteiras());
    }
}
