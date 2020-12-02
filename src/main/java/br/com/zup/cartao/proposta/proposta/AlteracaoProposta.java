package br.com.zup.cartao.proposta.proposta;

import br.com.zup.cartao.proposta.cartao.Cartao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class AlteracaoProposta extends Proposta {
    public AlteracaoProposta(@NotBlank(message = "documento é um campo de preenchimento obrigatório.") String documento,
                             @NotBlank(message = "email é um campo de preenchimento obrigatório.") @Email String email,
                             @NotBlank(message = "nome é um campo de preenchimento obrigatório.") String nome,
                             @NotBlank(message = "endereco é um campo de preenchimento obrigatório.") String endereco,
                             @NotNull(message = "salário é um campo de preenchimento obrigatório.") @Positive BigDecimal salario,
                             @NotNull PropostaElegibilidade propostaElegibilidade) {
        super(documento, email, nome, endereco, salario, propostaElegibilidade);
    }

    public AlteracaoProposta(Proposta proposta) {

        super(proposta.getNome(), proposta.getEmail(), proposta.getDocumento(), proposta.getEndereco(), proposta.getSalario(), proposta.getPropostaElegibilidade());

    }

    public Proposta aprovaVinculacaoCartao(Cartao cartao) {
       if(cartao != null) {
           this.setPropostaSituacao(PropostaSituacao.APROVADA);
           this.setCartao(cartao);
        return this;
       }
       return this;
    }
}
