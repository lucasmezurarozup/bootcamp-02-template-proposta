package br.com.zup.cartao.proposta.compartilhado.exceptions;

public class Erro {
    private String campo;
    private String messagem;

    public Erro(String campo, String messagem) {
        this.campo = campo;
        this.messagem = messagem;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }

    public void setMessagem(String messagem) {
        this.messagem = messagem;
    }

    public String getMessagem() {
        return messagem;
    }
}
