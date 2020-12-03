package br.com.zup.cartao.proposta.compartilhado.cartao;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient(name = "cartoes", url = "${apis_externas.cartoes.url:http://localhost:8888/api}")
public interface CartaoClient {

    @RequestLine("POST")
    @RequestMapping(method = RequestMethod.POST, value = "/cartoes")
    public ResponseEntity<?> consultaDisponibilidade(@RequestBody ConsultaDisponibilidadeVinculacaoCartaoRequest consultaDisponibilidadeVinculacaoCartao);

    @RequestLine("GET")
    @RequestMapping(method = RequestMethod.GET, value = "/cartoes/{id}")
    public ResultadoConsultaCartaoResponse informacoesCartao(@PathVariable("id") String id);


}
