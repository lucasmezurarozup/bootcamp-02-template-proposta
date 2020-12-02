package br.com.zup.cartao.proposta.proposta;

import br.com.zup.cartao.proposta.cartao.Cartao;

import java.math.BigDecimal;

public class AcompanhamentoPropostaResponse {
    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;
    private PropostaElegibilidade propostaElegibilidade;
    private PropostaSituacao propostaSituacao;
    private Cartao cartao;

    @Deprecated
    public AcompanhamentoPropostaResponse() {

    }

    public AcompanhamentoPropostaResponse(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.propostaElegibilidade = proposta.getPropostaElegibilidade();
        this.propostaSituacao = proposta.getPropostaSituacao();
        this.cartao = proposta.getCartao();
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public PropostaElegibilidade getPropostaElegibilidade() {
        return propostaElegibilidade;
    }

    public PropostaSituacao getPropostaSituacao() {
        return propostaSituacao;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
