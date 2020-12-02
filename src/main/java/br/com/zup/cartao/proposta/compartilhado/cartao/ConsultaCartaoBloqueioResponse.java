package br.com.zup.cartao.proposta.compartilhado.cartao;

import br.com.zup.cartao.proposta.cartao.BloqueioCartao;

import java.time.LocalDateTime;

public class ConsultaCartaoBloqueioResponse {
    private String id;
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    private boolean ativo;

    public ConsultaCartaoBloqueioResponse(String id, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public String getId() {
        return id;
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

    public BloqueioCartao toCartaoBloqueio() {
        return new BloqueioCartao(this.id, this.bloqueadoEm, this.sistemaResponsavel, this.ativo);
    }
}
