package br.com.zup.cartao.proposta.compartilhado.cartao;

import java.time.LocalDate;

public class ConsultaCartaoAvisoResponse {
    private LocalDate validoAte;
    private String destino;

    public ConsultaCartaoAvisoResponse(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
