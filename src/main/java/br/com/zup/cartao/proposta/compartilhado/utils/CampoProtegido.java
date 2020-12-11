package br.com.zup.cartao.proposta.compartilhado.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class CampoProtegido implements AttributeConverter<String, String> {

    private static CriptografiaConfig criptografiaConfig;

    public CampoProtegido(@Value("${criptografia}") String codigoSeg) throws NoSuchPaddingException, NoSuchAlgorithmException {
        criptografiaConfig = new CriptografiaConfig(codigoSeg);
    }

    @Override
    public String convertToDatabaseColumn(String campo) {
        try {
            return criptografiaConfig.encrypt(campo);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException exception) {
            throw new IllegalStateException(exception);
        }
    }

    @Override
    public String convertToEntityAttribute(String campoCriptado) {
        try {
            return criptografiaConfig.decrypt(campoCriptado);
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new IllegalStateException(e);
        }
    }
}