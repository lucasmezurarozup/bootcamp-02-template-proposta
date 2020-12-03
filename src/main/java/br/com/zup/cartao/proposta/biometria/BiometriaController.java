package br.com.zup.cartao.proposta.biometria;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@Validated
@RestController
@RequestMapping("/biometria")
public class BiometriaController {

    @PostMapping("/cartao/{id}")
    public ResponseEntity<?> cadastrarBiometria(@PathVariable("id") String id, @Valid @RequestBody CadastrarBiometriaRequest cadastrarBiometriaRequest) {

        return null;
    }
}
