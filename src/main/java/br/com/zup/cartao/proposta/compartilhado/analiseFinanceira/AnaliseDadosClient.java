package br.com.zup.cartao.proposta.compartilhado.analiseFinanceira;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "analiseDados", url = "http://localhost:9999/api/")
public interface AnaliseDadosClient {

    @RequestLine("POST")
    @RequestMapping(method = RequestMethod.POST, value = "/solicitacao")
    ResultadoSolicitacaoAnaliseCliente solicitacaoAnalise(@RequestBody NovaSolicitacaoAnaliseClienteRequest novaSolicitacaoAnaliseCliente);
}
