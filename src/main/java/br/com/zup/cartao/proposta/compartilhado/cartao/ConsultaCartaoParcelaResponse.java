package br.com.zup.cartao.proposta.compartilhado.cartao;

import java.math.BigDecimal;

public class ConsultaCartaoParcelaResponse {
    private String id;
    private BigDecimal quantidade;
    private BigDecimal valor;

    public ConsultaCartaoParcelaResponse(String id, BigDecimal quantidade, BigDecimal valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
