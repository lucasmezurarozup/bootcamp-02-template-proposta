package br.com.zup.cartao.proposta.proposta;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VerificaDocumentoValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NovaPropostaRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        NovaPropostaRequest novaCompraRequest = (NovaPropostaRequest) o;
        if(!novaCompraRequest.validaDocumento()) {
            errors.rejectValue("documento", null, "o documento deve estar preenchido corretamente.");
            return;
        }

    }
}
