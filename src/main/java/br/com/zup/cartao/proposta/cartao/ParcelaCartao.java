package br.com.zup.cartao.proposta.cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(
        name = "parcelas_cartao"
)
public class ParcelaCartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroParcela;
    private BigDecimal quantidade;
    private BigDecimal valor;

    @Deprecated
    private ParcelaCartao() {

    }

    public ParcelaCartao(String numeroParcela, BigDecimal quantidade, BigDecimal valor) {
        this.numeroParcela = numeroParcela;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroParcela() {
        return numeroParcela;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }
}

