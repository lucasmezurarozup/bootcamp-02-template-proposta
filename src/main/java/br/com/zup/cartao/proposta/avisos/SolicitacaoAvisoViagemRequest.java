package br.com.zup.cartao.proposta.avisos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class SolicitacaoAvisoViagemRequest {
    @NotBlank
    private String destino;

    @NotNull
    @Future
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate validoAte;

    public SolicitacaoAvisoViagemRequest() {

    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
