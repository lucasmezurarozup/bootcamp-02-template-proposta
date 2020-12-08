package br.com.zup.cartao.proposta.proposta;

import br.com.zup.cartao.proposta.cartao.Cartao;
import br.com.zup.cartao.proposta.compartilhado.cartao.BloqueioCartaoResultadoResponse;
import br.com.zup.cartao.proposta.compartilhado.cartao.ConsultaCartaoBloqueioResponse;
import br.com.zup.cartao.proposta.compartilhado.cartao.ResultadoConsultaCartaoResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AcompanhamentoPropostaCartaoVinculadoResponse {
    private String titular;
    private LocalDateTime emitidoEm;
    private String numeroCartao;
    private List<ConsultaCartaoBloqueioResponse> bloqueios;

    public List<ConsultaCartaoBloqueioResponse> getBloqueios() {
        return bloqueios;
    }



    public AcompanhamentoPropostaCartaoVinculadoResponse(Cartao cartao) {
        this.titular = cartao.getTitular();
        this.emitidoEm = cartao.getEmitidoEm();
        this.numeroCartao = cartao.getNumeroCartao();
        this.bloqueios = cartao.getBloqueios()
                .stream()
                .map(bloqueioCartao ->
                        new ConsultaCartaoBloqueioResponse(bloqueioCartao.getNumeroBloqueio(),
                                bloqueioCartao.getBloqueadoEm(),
                                bloqueioCartao.getSistemaResponsavel(),
                                bloqueioCartao.isAtivo())).collect(Collectors.toList());
    }

    public AcompanhamentoPropostaCartaoVinculadoResponse() {

    }

    public String getTitular() {
        return titular;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
