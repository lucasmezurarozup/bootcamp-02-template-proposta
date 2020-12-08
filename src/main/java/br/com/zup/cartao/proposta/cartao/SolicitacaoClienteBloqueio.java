package br.com.zup.cartao.proposta.cartao;

import org.bouncycastle.util.IPAddress;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "solicitacoes_bloqueio"
)
public class SolicitacaoClienteBloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCartao;
    private LocalDateTime solicitadoEm = LocalDateTime.now();
    private String ip;
    private String userAgent;

    private SolicitacaoClienteBloqueio() {

    }
    public SolicitacaoClienteBloqueio(String numeroCartao, String ip, String userAgent) {
        this.numeroCartao = numeroCartao;
        this.ip = ip;
        this.userAgent = userAgent;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public LocalDateTime getSolicitadoEm() {
        return solicitadoEm;
    }

    public String getIp() {
        return ip;
    }

    public String getUserAgent() {
        return userAgent;
    }
}




