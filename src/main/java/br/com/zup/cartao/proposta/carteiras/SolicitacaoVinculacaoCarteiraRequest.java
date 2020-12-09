package br.com.zup.cartao.proposta.carteiras;

import br.com.zup.cartao.proposta.avisos.SolicitacaoAvisoViagemRequest;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SolicitacaoVinculacaoCarteiraRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String carteira;

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
