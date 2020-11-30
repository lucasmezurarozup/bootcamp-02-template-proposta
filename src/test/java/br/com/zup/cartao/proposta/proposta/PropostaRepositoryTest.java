package br.com.zup.cartao.proposta.proposta;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

@DataJpaTest
public class PropostaRepositoryTest {

    @Autowired
    private PropostaRepository propostaRepository;

    @Test
    public void insereComDocumentoNulo() {
        Proposta proposta = new Proposta(null, "lucas@zup.com.br", "lucas", "rua 2", BigDecimal.valueOf(1000.00));
        try {
            propostaRepository.save(proposta);
        }catch(Exception e) {
            Assertions.assertThat(e).isInstanceOf(ConstraintViolationException.class);
        }
    }

    @Test
    public void insereComEmailNulo() {
        Proposta proposta = new Proposta("516.170.390-39", null, "lucas", "rua 2", BigDecimal.valueOf(1000.00));
        try {
            propostaRepository.save(proposta);
        }catch(Exception e) {
            Assertions.assertThat(e).isInstanceOf(ConstraintViolationException.class);
        }
    }

    @Test
    public void insereComNomeNulo() {
        Proposta proposta = new Proposta("516.170.390-39", "lucas@zup.com.br", null, "rua 2", BigDecimal.valueOf(1000.00));
        try {
            propostaRepository.save(proposta);
        }catch(Exception e) {
            Assertions.assertThat(e).isInstanceOf(ConstraintViolationException.class);
        }
    }

    @Test
    public void insereComEnderecoNulo() {
        Proposta proposta = new Proposta("516.170.390-39", "lucas@zup.com.br", "lucas", null, BigDecimal.valueOf(1000.00));
        try {
            propostaRepository.save(proposta);
        }catch(Exception e) {
            Assertions.assertThat(e).isInstanceOf(ConstraintViolationException.class);
        }
    }

    @Test
    public void insereComSalarioNulo() {
        Proposta proposta = new Proposta("516.170.390-39", "lucas@zup.com.br", "lucas", "rua 2", null);
        try {
            propostaRepository.save(proposta);
        }catch(Exception e) {
            Assertions.assertThat(e).isInstanceOf(ConstraintViolationException.class);
        }
    }

    @Test
    public void insereComSalarioZerado() {
        Proposta proposta = new Proposta("516.170.390-39", "lucas@zup.com.br", "lucas", "rua 2", BigDecimal.valueOf(0));
        try {
            propostaRepository.save(proposta);
        }catch(Exception e) {
            Assertions.assertThat(e).isInstanceOf(ConstraintViolationException.class);
        }
    }

    @Test
    public void insereComSalarioNegativo() {
        Proposta proposta = new Proposta("516.170.390-39", "lucas@zup.com.br", "lucas", "rua 2", BigDecimal.valueOf(-1));
        try {
            propostaRepository.save(proposta);
        }catch(Exception e) {
            Assertions.assertThat(e).isInstanceOf(ConstraintViolationException.class);
        }
    }
}
