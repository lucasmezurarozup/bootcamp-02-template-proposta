package br.com.zup.cartao.proposta.cartao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "bloqueios_cartao"
)
public class BloqueioCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroBloqueio;
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    private boolean ativo;

    @Deprecated
    private BloqueioCartao() {

    }

    public BloqueioCartao( String numeroBloqueio, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo) {
        this.numeroBloqueio = numeroBloqueio;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroBloqueio() {
        return numeroBloqueio;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
