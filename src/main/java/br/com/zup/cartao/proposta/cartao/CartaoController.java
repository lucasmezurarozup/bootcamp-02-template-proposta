package br.com.zup.cartao.proposta.cartao;

import br.com.zup.cartao.proposta.compartilhado.cartao.BloquearCartaoRequest;
import br.com.zup.cartao.proposta.compartilhado.cartao.BloqueioCartaoResultadoResponse;
import br.com.zup.cartao.proposta.compartilhado.cartao.CartaoClient;
import br.com.zup.cartao.proposta.compartilhado.cartao.ResultadoConsultaCartaoResponse;
import br.com.zup.cartao.proposta.compartilhado.utils.InformacoesEquipamentoUsuario;
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

        Cartao cartao = retornaCartaoAssociadoAoNumero(numeroCartao);

        SolicitacaoClienteBloqueio solicitacaoClienteBloqueio =
                new InformacoesEquipamentoUsuario()
                        .registraInformacoesCliente(request, numeroCartao);

        solicitacaoClienteBloqueioRepository.save(solicitacaoClienteBloqueio);

        gravaVinculacaoBloqueioCartao(cartao);

        return ResponseEntity.ok(bloqueioCartaoResultadoResponse);
    }

    public void gravaVinculacaoBloqueioCartao(Cartao cartao) {
        Proposta proposta = propostaRepository
                .findByCartaoNumeroCartao(cartao.getNumeroCartao())
                .orElseThrow();

        proposta.setCartao(cartao);
        propostaRepository.save(proposta);

        logger.info("cartão bloqueado com sucesso!");

    }

    public Cartao retornaCartaoAssociadoAoNumero(String numeroCartao) {
        ResultadoConsultaCartaoResponse resultadoConsultaCartaoResponse =
                cartaoClient.informacoesCartao(numeroCartao);

        return new CartaoBuilder()
                .buildCartao(resultadoConsultaCartaoResponse);

    }
}
