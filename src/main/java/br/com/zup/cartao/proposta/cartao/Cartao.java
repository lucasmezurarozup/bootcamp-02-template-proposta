package br.com.zup.cartao.proposta.cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "cartoes"
)
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String numeroCartao;

    @NotNull
    private LocalDateTime emitidoEm;

    @NotNull
    private String titular;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    private List<BloqueioCartao> bloqueios;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AvisoCartao> avisos;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    private List<CarteiraCartao> carteiras;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    private List<ParcelaCartao> parcelas;

    @NotNull
    @Positive
    private BigDecimal limite;

    @OneToOne
    private RenegociacaoCartao renegociacao;

    @OneToOne
    private VencimentoCartao vencimento;

    @NotNull
    private Long idProposta;

    @Deprecated
    private Cartao() {

    }

    public Cartao(@NotNull String numeroCartao,
                  @NotNull LocalDateTime emitidoEm,
                  @NotNull String titular,
                  @NotNull List<BloqueioCartao> bloqueios,
                  List<AvisoCartao> avisos,
                  @NotNull List<CarteiraCartao> carteiras,
                  @NotNull List<ParcelaCartao> parcelas,
                  @NotNull @Positive BigDecimal limite,
                  RenegociacaoCartao renegociacao,
                  VencimentoCartao vencimento,
                  @NotNull Long idProposta) {

        this.numeroCartao = numeroCartao;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<BloqueioCartao> getBloqueios() {
        return bloqueios;
    }

    public List<AvisoCartao> getAvisos() {
        return avisos;
    }

    public List<CarteiraCartao> getCarteiras() {
        return carteiras;
    }

    public List<ParcelaCartao> getParcelas() {
        return parcelas;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public RenegociacaoCartao getRenegociacao() {
        return renegociacao;
    }

    public VencimentoCartao getVencimento() {
        return vencimento;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
