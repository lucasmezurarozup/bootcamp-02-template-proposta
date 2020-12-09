package br.com.zup.cartao.proposta.avisos;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "solicitacoes_aviso_viagem"
)
public class SolicitacaoAvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String destino;
    @NotNull
    @Future
    private LocalDate dataTerminoViagem;
    private LocalDateTime dataCriacaoSolicitacao = LocalDateTime.now();
    @NotBlank
    private String numeroCartao;
    @NotBlank
    private String ipCliente;
    @NotBlank
    private String userAgent;

    private SolicitacaoAvisoViagem() {

    }

    public SolicitacaoAvisoViagem(@NotBlank String destino,
                                  @NotBlank LocalDate dataTerminoViagem,
                                  @NotBlank String numeroCartao,
                                  @NotBlank String ipCliente,
                                  @NotBlank String userAgent) {
        this.destino = destino;
        this.dataTerminoViagem = dataTerminoViagem;
        this.numeroCartao = numeroCartao;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
    }

    public Long getId() {
        return id;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTerminoViagem() {
        return dataTerminoViagem;
    }

    public LocalDateTime getDataCriacaoSolicitacao() {
        return dataCriacaoSolicitacao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
