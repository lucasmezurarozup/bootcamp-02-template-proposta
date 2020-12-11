package br.com.zup.cartao.proposta.compartilhado.utils;

import org.springframework.beans.factory.annotation.Value;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CriptografiaConfig {
    private static String codigoSeg;
    private final String tipoAlgoritmo = "AES";
    private final Cipher cipher;
    private final Key chave;

    public CriptografiaConfig(String codigoSeg) throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.cipher = Cipher.getInstance(tipoAlgoritmo);
        this.chave = new SecretKeySpec(codigoSeg.getBytes(), tipoAlgoritmo);
    }

    public String encrypt(String campo) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        this.getCipher().init(cipher.ENCRYPT_MODE, chave);
        return Base64.getEncoder().encodeToString(cipher.doFinal(campo.getBytes()));
    }
    public String decrypt(String campoCriptado) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.DECRYPT_MODE, chave);
        return new String(cipher.doFinal(Base64.getDecoder().decode(campoCriptado)));
    }

    public static String getCodigoSeg() {
        return codigoSeg;
    }

    public String getTipoAlgoritmo() {
        return tipoAlgoritmo;
    }

    public Cipher getCipher() {
        return cipher;
    }

    public Key getChave() {
        return chave;
    }
}
