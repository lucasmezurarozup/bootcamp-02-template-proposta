package br.com.zup.cartao.proposta.cartao;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "carteiras_cartao"
)
public class CarteiraCartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroCarteira;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    @Deprecated
    private CarteiraCartao() {

    }

    public CarteiraCartao(String numeroCarteira, String email, LocalDateTime associadaEm, String emissor) {
        this.numeroCarteira = numeroCarteira;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroCarteira() {
        return numeroCarteira;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }
}
