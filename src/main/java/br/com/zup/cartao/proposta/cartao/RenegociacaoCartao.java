package br.com.zup.cartao.proposta.cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(
        name = "renegociacoes_cartao"
)
public class RenegociacaoCartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroRenegociacao;
    private BigDecimal quantidade;
    private BigDecimal valor;
    private LocalDateTime dataCriacao;

    @Deprecated
    private RenegociacaoCartao() {

    }

    public RenegociacaoCartao(String numeroRenegociacao, BigDecimal quantidade, BigDecimal valor, LocalDateTime dataCriacao) {
        this.numeroRenegociacao = numeroRenegociacao;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataCriacao = dataCriacao;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroRenegociacao() {
        return numeroRenegociacao;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
