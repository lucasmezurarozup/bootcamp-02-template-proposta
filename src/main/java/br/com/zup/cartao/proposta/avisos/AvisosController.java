package br.com.zup.cartao.proposta.avisos;

import br.com.zup.cartao.proposta.cartao.SolicitacaoClienteBloqueio;
import br.com.zup.cartao.proposta.compartilhado.cartao.CartaoClient;
import br.com.zup.cartao.proposta.compartilhado.utils.InformacoesEquipamentoUsuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/avisos")
public class AvisosController {

    private Logger logger = LoggerFactory.getLogger(AvisosController.class);

    @Autowired
    private CartaoClient cartaoClient;

    @Autowired
    private SolicitacaoAvisoViagemRepository solicitacaoAvisoViagemRepository;

    @PostMapping("/viagem/cartao/{id}")
    @Transactional
    public ResponseEntity<?> avisarDeslocamento(@PathVariable("id") String numeroCartao, @Valid @RequestBody SolicitacaoAvisoViagemRequest solicitacaoAvisoViagemRequest, HttpServletRequest httpServletRequest) {

        logger.info("inicia procedimento de aviso de deslocamento");

       SolicitacaoAvisoViagemResponse solicitacaoAvisoViagemResponse = cartaoClient.avisoViagem(numeroCartao, solicitacaoAvisoViagemRequest);

       logger.info("capturando as informações do requerente");

        InformacoesEquipamentoUsuario informacoesEquipamentoUsuario = new InformacoesEquipamentoUsuario();
        SolicitacaoClienteBloqueio solicitacaoClienteBloqueio = informacoesEquipamentoUsuario.registraInformacoesCliente(httpServletRequest, numeroCartao);


       SolicitacaoAvisoViagem solicitacaoAvisoViagem = new SolicitacaoAvisoViagem(solicitacaoAvisoViagemRequest.getDestino(),
               solicitacaoAvisoViagemRequest.getValidoAte(),
               numeroCartao,
               solicitacaoClienteBloqueio.getIp(),
               solicitacaoClienteBloqueio.getUserAgent());

       solicitacaoAvisoViagemRepository.save(solicitacaoAvisoViagem);

       logger.info("aviso de deslocamento registrado com sucesso!");

       return ResponseEntity.ok(solicitacaoAvisoViagemResponse);

    }

}
