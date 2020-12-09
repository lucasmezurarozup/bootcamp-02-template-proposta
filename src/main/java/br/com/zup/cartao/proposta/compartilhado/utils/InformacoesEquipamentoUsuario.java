package br.com.zup.cartao.proposta.compartilhado.utils;

import br.com.zup.cartao.proposta.cartao.SolicitacaoClienteBloqueio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class InformacoesEquipamentoUsuario {

    private final Logger logger = LoggerFactory.getLogger(InformacoesEquipamentoUsuario.class);

    public InformacoesEquipamentoUsuario() {

    }


    public SolicitacaoClienteBloqueio registraInformacoesCliente(HttpServletRequest request, String numeroCartao) {

        String ip = Optional.ofNullable(request.getHeader("X-FORWARED-FOR")).orElse(request.getRemoteAddr());
        if(ip.equals("0:0:0:0:0:0:0:1")) ip = "127.0.0.1";

        SolicitacaoClienteBloqueio solicitacaoClienteBloqueio = new SolicitacaoClienteBloqueio(
                numeroCartao,
                ip,
                request.getHeader("User-Agent"));

        logger.info("gravando informações do requerente do bloqueio");

        return solicitacaoClienteBloqueio;
        /*solicitacaoClienteBloqueioRepository
                .save(solicitacaoClienteBloqueio);*/
    }
}
