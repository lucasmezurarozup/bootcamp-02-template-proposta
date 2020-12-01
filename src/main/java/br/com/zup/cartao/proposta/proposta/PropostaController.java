package br.com.zup.cartao.proposta.proposta;

import br.com.zup.cartao.proposta.compartilhado.AnaliseDadosClient;
import br.com.zup.cartao.proposta.compartilhado.NovaSolicitacaoAnaliseClienteRequest;
import br.com.zup.cartao.proposta.compartilhado.ResultadoSolicitacaoAnaliseCliente;
import feign.Feign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Validated
@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    AnaliseDadosClient analiseCliente;

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping("/nova")
    @Transactional
    public ResponseEntity<?> criaProposta(@Valid @RequestBody NovaPropostaRequest novaPropostaRequest, UriComponentsBuilder uriComponentsBuilder) {

        ResultadoSolicitacaoAnaliseCliente resultadoSolicitacaoAnaliseCliente = analiseCliente
                .solicitacaoAnalise(
                        new NovaSolicitacaoAnaliseClienteRequest(
                                novaPropostaRequest.getDocumento(),
                                novaPropostaRequest.getNome(),
                                null)
                );

        PropostaElegibilidade elegibilidadeProposta =
                verificaElegibilidade(resultadoSolicitacaoAnaliseCliente);

        Proposta proposta = propostaRepository.save(
                novaPropostaRequest.toModel(elegibilidadeProposta));

        URI location = uriComponentsBuilder
                .path("/propostas/detalhes/{id}")
                .buildAndExpand(proposta.getId())
                .toUri();

        logger.info("Proposta documento {} e salario {} registrada com sucesso!", proposta.getDocumento(), proposta.getSalario());

        return ResponseEntity.created(location).build();
    }

    public PropostaElegibilidade verificaElegibilidade(ResultadoSolicitacaoAnaliseCliente resultadoSolicitacaoAnaliseCliente) {
        PropostaElegibilidade propostaElegibilidade;
        if (resultadoSolicitacaoAnaliseCliente.getResultadoSolicitacao() == "COM_RESTRICAO") {
            propostaElegibilidade = PropostaElegibilidade.NAO_ELEGIVEL;
        } else {
            propostaElegibilidade = PropostaElegibilidade.ELEGIVEL;
        }

        return propostaElegibilidade;
    }

    @GetMapping("/acompanhamento/{id}")
    public ResponseEntity<?> detalhesProposta(@PathVariable("id") Long id) {
        Proposta proposta = this.propostaRepository.findById(id).orElseThrow();
        AcompanhamentoPropostaResponse acompanhamentoPropostaResponse = new AcompanhamentoPropostaResponse(proposta);
        return ResponseEntity.ok(acompanhamentoPropostaResponse);
    }
}
