package br.com.zup.cartao.proposta.proposta;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {

   @NotBlank(message = "documento é um campo de preenchimento obrigatório.")
   private String documento;

   @NotBlank(message = "email é um campo de preenchimento obrigatório.")
   @Email
   private String email;

   @NotBlank(message = "nome é um campo de preenchimento obrigatório.")
   private String nome;

   @NotBlank(message = "endereco é um campo de preenchimento obrigatório.")
   private String endereco;

   @NotNull(message = "salário é um campo de preenchimento obrigatório.")
   @Positive
   private BigDecimal salario;

   @Deprecated
   public NovaPropostaRequest() {

   }

   public NovaPropostaRequest(@NotBlank(message = "documento é um campo de preenchimento obrigatório.") String documento,
                              @NotBlank(message = "email é um campo de preenchimento obrigatório.") @Email String email,
                              @NotBlank(message = "nome é um campo de preenchimento obrigatório.") String nome,
                              @NotBlank(message = "endereco é um campo de preenchimento obrigatório.") String endereco,
                              @NotNull(message = "salário é um campo de preenchimento obrigatório.") @Positive BigDecimal salario) {
      this.documento = documento;
      this.email = email;
      this.nome = nome;
      this.endereco = endereco;
      this.salario = salario;
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

   public boolean validaDocumento() {
      String documento = this.getDocumento();

      CPFValidator cpfValidator = new CPFValidator();
      cpfValidator.initialize(null);
      CNPJValidator cnpjValidator = new CNPJValidator();
      cnpjValidator.initialize(null);

      return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null);
   }

   public Proposta toModel(PropostaElegibilidade propostaElegibilidade) {
       return new Proposta(this.documento, this.email, this.nome, this.endereco, this.salario, propostaElegibilidade);
   }
}
