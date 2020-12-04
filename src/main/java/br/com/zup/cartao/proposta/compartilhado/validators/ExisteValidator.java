package br.com.zup.cartao.proposta.compartilhado.validators;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExisteValidator implements ConstraintValidator<Existe, String> {

    @PersistenceContext
    EntityManager manager;

    private String campo;
    private Class<?> entidade;
    @Override
    public void initialize(Existe constraintAnnotation) {
        campo = constraintAnnotation.nomeCampo();
        entidade = constraintAnnotation.entidade();
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {

        if(id == null) return true;

        Query query = manager.createQuery("select 1 from "+entidade.getName()+" where "+campo+"=:value");
        query.setParameter("value", id);

        return !query.getResultList().isEmpty();


    }
}