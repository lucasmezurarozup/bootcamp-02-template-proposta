package br.com.zup.cartao.proposta.carteiras;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(
        name = "carteiras_digitais"
)
public class CarteiraDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String numeroCartao;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String carteira;

    private CarteiraDigital() {

    }

    public CarteiraDigital(@NotBlank @Email String email, @NotBlank String carteira, @NotBlank String numeroCartao) {
        this.email = email;
        this.carteira = carteira;
        this.numeroCartao = numeroCartao;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
