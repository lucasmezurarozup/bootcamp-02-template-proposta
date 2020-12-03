package br.com.zup.cartao.proposta.compartilhado.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
@Constraint(validatedBy = ExisteValidator.class)
public @interface Existe {
    String message() default "não registrado em nossa base de dados";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String nomeCampo();
    Class<?> entidade();
}
