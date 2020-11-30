package br.com.zup.cartao.proposta.proposta;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(
        name = "propostas"
)
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private Proposta() {

    }

    public Proposta(@NotBlank(message = "documento é um campo de preenchimento obrigatório.") String documento,
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

    public Long getId() {
        return id;
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
}
