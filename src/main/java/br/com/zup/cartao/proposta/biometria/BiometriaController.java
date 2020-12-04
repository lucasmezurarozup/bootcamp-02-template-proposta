package br.com.zup.cartao.proposta.biometria;

import br.com.zup.cartao.proposta.proposta.Proposta;
import br.com.zup.cartao.proposta.proposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/biometria")
public class BiometriaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping("/cartao/{id}")
    @Transactional
    public ResponseEntity<?> cadastrarBiometria(@PathVariable("id") String idCartao, @Valid @RequestParam MultipartFile fingerprint) throws IOException {

        Proposta proposta = propostaRepository.findByCartaoNumeroCartao(idCartao).orElseThrow();
        Biometria biometria = new Biometria(idCartao, fingerprint.getBytes(), fingerprint.getContentType(), fingerprint.getSize(), fingerprint.getOriginalFilename());
        List<Biometria> biometriaList = new ArrayList<>();
        if (!proposta.getCartao().getBiometria().isEmpty()) {
            biometriaList.addAll(proposta.getCartao().getBiometria());
        }
        biometriaList.add(biometria);
        proposta.getCartao().setBiometria(biometriaList);
        propostaRepository.save(proposta);
        return ResponseEntity.ok(proposta);
    }
}
