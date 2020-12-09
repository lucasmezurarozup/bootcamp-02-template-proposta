package br.com.zup.cartao.proposta.cartao;

import br.com.zup.cartao.proposta.compartilhado.cartao.ResultadoConsultaCartaoResponse;

public class CartaoBuilder {

    public CartaoBuilder() {

    }

    public Cartao buildCartao(ResultadoConsultaCartaoResponse resultadoConsultaCartaoResponse) {
        Cartao cartao = new Cartao(
                resultadoConsultaCartaoResponse.getId(),
                resultadoConsultaCartaoResponse.getEmitidoEm(),
                resultadoConsultaCartaoResponse.getTitular(),
                resultadoConsultaCartaoResponse.getLimite(),
                Long.valueOf(resultadoConsultaCartaoResponse.getIdProposta())
        )
                .buildBloqueios(resultadoConsultaCartaoResponse.getBloqueios())
                .buildAvisos(resultadoConsultaCartaoResponse.getAvisos())
                .buildCarteiras(resultadoConsultaCartaoResponse.getCarteiras())
                .buildParcelas(resultadoConsultaCartaoResponse.getParcelas())
                .buildRenegociacao(resultadoConsultaCartaoResponse.getRenegociacao())
                .buildVencimento(resultadoConsultaCartaoResponse.getVencimento());

        return cartao;
    }
}
