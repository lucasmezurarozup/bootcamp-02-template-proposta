package br.com.zup.cartao.proposta.cartao;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "vencimentos_cartao"
)
public class VencimentoCartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroVencimento;
    private int dia;
    private LocalDateTime dataCriacao;

    @Deprecated
    private VencimentoCartao() {

    }

    public VencimentoCartao(String numeroVencimento, int dia, LocalDateTime dataCriacao) {
        this.numeroVencimento = numeroVencimento;
        this.dia = dia;
        this.dataCriacao = dataCriacao;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroVencimento() {
        return numeroVencimento;
    }

    public int getDia() {
        return dia;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
