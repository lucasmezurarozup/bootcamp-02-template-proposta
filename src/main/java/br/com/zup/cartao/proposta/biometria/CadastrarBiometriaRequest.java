package br.com.zup.cartao.proposta.biometria;

import br.com.zup.cartao.proposta.cartao.Cartao;
import br.com.zup.cartao.proposta.compartilhado.validators.Existe;
import com.sun.istack.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

public class CadastrarBiometriaRequest {
    /*@NotNull
    @Existe(nomeCampo = "numero_cartao", entidade = Cartao.class)
    String cartaoId;*/
    @NotNull
    MultipartFile fingerprint;

    public CadastrarBiometriaRequest(MultipartFile fingerprint) {

        this.fingerprint = fingerprint;
    }


    public MultipartFile getFingerprint() {
        return fingerprint;
    }
}
