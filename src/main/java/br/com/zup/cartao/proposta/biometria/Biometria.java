package br.com.zup.cartao.proposta.biometria;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Base64;

@Entity
@Table(
        name = "biometrias"
)
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCartao;

    private byte[] fingerprint;

    private String tipo;

    private Long tamanho;

    private String nomeArquivo;

    @Deprecated
    private Biometria() {

    }

    public Biometria(String numeroCartao, byte[] fingerprint, String tipo, Long tamanho, String nomeArquivo) {
        this.numeroCartao = numeroCartao;
        this.fingerprint = fingerprint;
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.nomeArquivo = nomeArquivo;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public byte[] getFingerprint() {
        return fingerprint;
    }

    public String getTipo() {
        return tipo;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }
}
