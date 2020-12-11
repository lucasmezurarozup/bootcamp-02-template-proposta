package br.com.zup.cartao.proposta.cartao;

import br.com.zup.cartao.proposta.biometria.Biometria;
import br.com.zup.cartao.proposta.compartilhado.cartao.*;
import br.com.zup.cartao.proposta.compartilhado.utils.CampoProtegido;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(
        name = "cartoes"
)
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CampoProtegido.class)
    @NotNull(message = "numeroCartao não deve ser nulo")
    private String numeroCartao;

    @NotNull(message = "emitidoEm não deve ser nulo")
    private LocalDateTime emitidoEm;

    @NotNull(message = "titular não deve ser nulo")
    private String titular;

    //@NotNull(message = "bloqueios não deve ser nulo")
    @OneToMany(cascade = CascadeType.ALL)
    private List<BloqueioCartao> bloqueios;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AvisoCartao> avisos;

    //@NotNull(message = "carteiras não deve ser nulo")
    @OneToMany(cascade = CascadeType.ALL)
    private List<CarteiraCartao> carteiras;

    //@NotNull(message = "parcelas não deve ser nulo")
    @OneToMany(cascade = CascadeType.ALL)
    private List<ParcelaCartao> parcelas;

    @NotNull(message = "limite não deve ser nulo")
    @Positive
    private BigDecimal limite;

    @OneToOne(cascade = CascadeType.ALL)
    private RenegociacaoCartao renegociacao;

    @OneToOne(cascade = CascadeType.ALL)
    private VencimentoCartao vencimento;

    @NotNull(message = "idProposta não deve ser nulo")
    private Long idProposta;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Biometria> biometria;

    public List<Biometria> getBiometria() {
        return biometria;
    }

    @Deprecated
    private Cartao() {

    }

    public void setBiometria(List<Biometria> biometria) {
        this.biometria = biometria;
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

    public Cartao(@NotNull String numeroCartao, @NotNull LocalDateTime emitidoEm, @NotNull String titular, @NotNull @Positive BigDecimal limite, @NotNull Long idProposta) {
        this.numeroCartao = numeroCartao;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.idProposta = idProposta;
    }

    public Cartao buildBloqueios(List<ConsultaCartaoBloqueioResponse> consultaCartaoBloqueioResponse) {

        this.bloqueios = consultaCartaoBloqueioResponse.stream()
                .map(consultaCartaoBloqueioResponse1 ->
                        new BloqueioCartao(consultaCartaoBloqueioResponse1.getId(),
                                consultaCartaoBloqueioResponse1.getBloqueadoEm(),
                                consultaCartaoBloqueioResponse1.getSistemaResponsavel(),
                                consultaCartaoBloqueioResponse1.isAtivo()))
                .collect(Collectors.toList());

        return this;
    }

    public Cartao buildAvisos(List<ConsultaCartaoAvisoResponse> consultaCartaoAvisoResponses) {
        this.avisos = consultaCartaoAvisoResponses.stream()
                .map(consultaCartaoAvisoResponse ->
                        new AvisoCartao(consultaCartaoAvisoResponse.getValidoAte(),
                                consultaCartaoAvisoResponse.getDestino()))
                .collect(Collectors.toList());
       return this;
    }

    public Cartao buildCarteiras(List<ConsultaCartaoCarteiraResponse> consultaCartaoCarteiraResponses) {
        this.carteiras = consultaCartaoCarteiraResponses.stream()
                .map(consultaCartaoCarteiraResponse ->
                        new CarteiraCartao(consultaCartaoCarteiraResponse.getId(),
                                consultaCartaoCarteiraResponse.getEmail(),
                                consultaCartaoCarteiraResponse.getAssociadaEm(),
                                consultaCartaoCarteiraResponse.getEmissor()))
                .collect(Collectors.toList());
        return this;
    }

    public Cartao buildParcelas(List<ConsultaCartaoParcelaResponse> consultaCartaoParcelaResponses) {
        this.parcelas = consultaCartaoParcelaResponses.stream()
                .map(consultaCartaoParcelaResponse ->
                        new ParcelaCartao(consultaCartaoParcelaResponse.getId(),
                                consultaCartaoParcelaResponse.getQuantidade(),
                                consultaCartaoParcelaResponse.getValor()))
                .collect(Collectors.toList());

        return this;
    }

    public Cartao buildRenegociacao(ConsultaCartaoRenegociacaoResponse consultaCartaoRenegociacaoResponses) {
        if(consultaCartaoRenegociacaoResponses != null) {
            this.renegociacao = new RenegociacaoCartao(consultaCartaoRenegociacaoResponses.getId(),
                    consultaCartaoRenegociacaoResponses.getQuantidade(),
                    consultaCartaoRenegociacaoResponses.getValor(),
                    consultaCartaoRenegociacaoResponses.getDataDeCriacao());
        }else {
            this.renegociacao = null;
        }
        return this;
    }

    public Cartao buildVencimento(ConsultaCartaoVencimentoResponse consultaCartaoVencimentoResponse) {

        this.vencimento = new VencimentoCartao(consultaCartaoVencimentoResponse.getId(),
                consultaCartaoVencimentoResponse.getDia(),
                consultaCartaoVencimentoResponse.getDataDeCriacao());

        return this;
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
