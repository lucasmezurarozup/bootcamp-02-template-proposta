package br.com.zup.cartao.proposta.proposta;

import br.com.zup.cartao.proposta.compartilhado.AnaliseDadosClient;
import br.com.zup.cartao.proposta.compartilhado.NovaSolicitacaoAnaliseClienteRequest;
import br.com.zup.cartao.proposta.compartilhado.ResultadoSolicitacaoAnaliseCliente;
import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Validated
@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    AnaliseDadosClient analiseCliente;

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping("/nova")
    @Transactional
    public ResponseEntity<?> criaProposta(@Valid @RequestBody NovaPropostaRequest novaPropostaRequest, UriComponentsBuilder uriComponentsBuilder) {

        ResultadoSolicitacaoAnaliseCliente resultadoSolicitacaoAnaliseCliente = analiseCliente
                .solicitacaoAnalise(
                        new NovaSolicitacaoAnaliseClienteRequest(novaPropostaRequest.getDocumento(),
                                novaPropostaRequest.getNome(),
                                null));
        PropostaSituacao elegibilidadeProposta =
                verificaElegibilidade(resultadoSolicitacaoAnaliseCliente);
        Proposta proposta = propostaRepository.save(
                novaPropostaRequest.toModel(elegibilidadeProposta));

        URI location = uriComponentsBuilder
                .path("/propostas/detalhes/{id}")
                .buildAndExpand(proposta.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    public PropostaSituacao verificaElegibilidade(ResultadoSolicitacaoAnaliseCliente resultadoSolicitacaoAnaliseCliente) {
        PropostaSituacao propostaSituacao;
        if(resultadoSolicitacaoAnaliseCliente.getResultadoSolicitacao() == "COM_RESTRICAO"){
            propostaSituacao = PropostaSituacao.NAO_ELEGIVEL;
        }else {
            propostaSituacao = PropostaSituacao.ELEGIVEL;
        }

        return propostaSituacao;
    }
}
