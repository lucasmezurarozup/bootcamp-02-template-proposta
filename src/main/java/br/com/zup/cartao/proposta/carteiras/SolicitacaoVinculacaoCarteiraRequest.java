package br.com.zup.cartao.proposta.carteiras;

import br.com.zup.cartao.proposta.avisos.SolicitacaoAvisoViagemRequest;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SolicitacaoVinculacaoCarteiraRequest {

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoCarteira carteira;

    public String getEmail() {
        return email;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }
}
