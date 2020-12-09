package br.com.zup.cartao.proposta.biometria;

import br.com.zup.cartao.proposta.proposta.Proposta;
import br.com.zup.cartao.proposta.proposta.PropostaController;
import br.com.zup.cartao.proposta.proposta.PropostaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/biometrias")
public class BiometriaController {

    @Autowired
    private PropostaRepository propostaRepository;

    private final Logger logger = LoggerFactory.getLogger(BiometriaController.class);

    @PostMapping("/cartao/{id}")
    @Transactional
    public ResponseEntity<?> cadastrarBiometria(@PathVariable("id") String idCartao, @Valid @RequestParam MultipartFile fingerprint) throws IOException {

        logger.info("iniciando processo de vinculacao de biometria ao cartão...");

        Proposta proposta = propostaRepository.findByCartaoNumeroCartao(idCartao).orElseThrow();

        logger.info("cartão encontrado, checando existencia de outras biometrias associadas...");

        Biometria biometria = new Biometria(idCartao, fingerprint.getBytes(), fingerprint.getContentType(), fingerprint.getSize(), fingerprint.getOriginalFilename());
        List<Biometria> biometrias = new ArrayList<>();

        logger.info("processando vinculação...");

        if (!proposta.getCartao().getBiometria().isEmpty()) {
            biometrias.addAll(proposta.getCartao().getBiometria());
        }

        biometrias.add(biometria);
        proposta.getCartao().setBiometria(biometrias);
        propostaRepository.save(proposta);

        logger.info("biometria salva com sucesso!");
        return ResponseEntity.ok(proposta);
    }
}
