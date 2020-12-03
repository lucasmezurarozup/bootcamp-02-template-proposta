package br.com.zup.cartao.proposta.biometria;

import br.com.zup.cartao.proposta.cartao.Cartao;
import br.com.zup.cartao.proposta.compartilhado.validators.Existe;
import com.sun.istack.NotNull;

import java.util.Base64;

public class CadastrarBiometriaRequest {
    @NotNull
    @Existe(nomeCampo = "numeroCartao", entidade = Cartao.class)
    String cartaoId;
    @NotNull
    Base64 fingerprint;

    public CadastrarBiometriaRequest(String cartaoId, Base64 fingerprint) {
        this.cartaoId = cartaoId;
        this.fingerprint = fingerprint;
    }

    public String getCartaoId() {
        return cartaoId;
    }

    public Base64 getFingerprint() {
        return fingerprint;
    }
}
