package br.com.zup.cartao.proposta.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private PropostaRepository propostaRepository;

    @PostMapping("/nova")
    public ResponseEntity<?> criaProposta(@Valid @RequestBody NovaPropostaRequest novaPropostaRequest, UriComponentsBuilder uriComponentsBuilder) {
        Proposta proposta = propostaRepository.save(novaPropostaRequest.toModel());
        URI location = uriComponentsBuilder
                .path("/detalhes/{id}")
                .buildAndExpand(proposta.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
