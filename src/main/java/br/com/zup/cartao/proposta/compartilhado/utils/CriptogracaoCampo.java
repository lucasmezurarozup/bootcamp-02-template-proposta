package br.com.zup.cartao.proposta.compartilhado.utils;

import javax.persistence.AttributeConverter;

public class CriptogracaoCampo implements AttributeConverter<String, String>{

    @Override
    public String convertToDatabaseColumn(String s) {
        return null;
    }

    @Override
    public String convertToEntityAttribute(String s) {
        return null;
    }
}
