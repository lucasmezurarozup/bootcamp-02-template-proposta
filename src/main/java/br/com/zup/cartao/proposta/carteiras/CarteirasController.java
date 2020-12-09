package br.com.zup.cartao.proposta.carteiras;

import br.com.zup.cartao.proposta.compartilhado.cartao.CartaoClient;
import br.com.zup.cartao.proposta.compartilhado.cartao.ResultadoConsultaCartaoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@Validated
@RestController
@RequestMapping("/carteiras")
public class CarteirasController {

    private final Logger logger = LoggerFactory.getLogger(CarteirasController.class);

    @Autowired
    private CarteiraDigitalRepository carteiraDigitalRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @PostMapping("/cartao/{id}")
    @Transactional
    public ResponseEntity<?> registrarCarteira(@PathVariable("id") String numeroCartao, @Valid @RequestBody SolicitacaoVinculacaoCarteiraRequest solicitacaoVinculacaoCarteiraRequest, UriComponentsBuilder uriComponentsBuilder) {
       logger.info("iniciando processo de vinculacao carteira digital");
        SolicitacaoVinculacaoCarteiraResponse solicitacaoVinculacaoCarteiraResponse = cartaoClient.vincularCarteiraDigital(numeroCartao, solicitacaoVinculacaoCarteiraRequest);

       CarteiraDigital carteiraDigital = new CarteiraDigital(solicitacaoVinculacaoCarteiraRequest.getEmail(), solicitacaoVinculacaoCarteiraRequest.getCarteira(), numeroCartao);

       carteiraDigitalRepository.save(carteiraDigital);

       logger.info("carteira digital vinculada com sucesso!");

       URI location = uriComponentsBuilder.path("/carteiras/cartao/{id}/{carteira}").buildAndExpand(numeroCartao, carteiraDigital.getId()).toUri();

       return ResponseEntity.created(location).build();
    }

    @GetMapping("/cartao/{id}/{carteira}")
    public ResponseEntity<?> detalhesCarteira(@PathVariable("id") String numeroCartao, @PathVariable("carteira") String carteira) {

        ResultadoConsultaCartaoResponse resultadoConsultaCartaoResponse = cartaoClient.informacoesCartao(numeroCartao);
        if (resultadoConsultaCartaoResponse != null) {
            CarteiraDigital carteiraDigital = carteiraDigitalRepository.findById(Long.valueOf(carteira)).orElseThrow();
            CarteiraDigitalResponse carteiraDigitalResponse = new CarteiraDigitalResponse(carteiraDigital.getEmail(), carteiraDigital.getCarteira(), carteiraDigital.getNumeroCartao());
            return ResponseEntity.ok(carteiraDigitalResponse);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
