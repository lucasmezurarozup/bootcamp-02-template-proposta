package br.com.zup.cartao.proposta.compartilhado.cartao;

public class BloquearCartaoRequest {
    private String id;

    public BloquearCartaoRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
