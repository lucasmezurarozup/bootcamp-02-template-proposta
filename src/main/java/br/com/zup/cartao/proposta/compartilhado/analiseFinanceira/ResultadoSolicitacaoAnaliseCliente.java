package br.com.zup.cartao.proposta.compartilhado.analiseFinanceira;

public class ResultadoSolicitacaoAnaliseCliente {
    private String documento;
    private String nome;
    private String resultadoSolicitacao;
    private String idProposta;


    private ResultadoSolicitacaoAnaliseCliente() {

    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
