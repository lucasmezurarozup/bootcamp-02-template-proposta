package br.com.zup.cartao.proposta.compartilhado.cartao;

import br.com.zup.cartao.proposta.avisos.SolicitacaoAvisoViagemRequest;
import br.com.zup.cartao.proposta.avisos.SolicitacaoAvisoViagemResponse;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "cartoes", url = "${apis_externas.cartoes.url:http://localhost:8888/api}")
public interface CartaoClient {

    @RequestLine("POST")
    @RequestMapping(method = RequestMethod.POST, value = "/cartoes")
    public ResponseEntity<?> consultaDisponibilidade(@RequestBody ConsultaDisponibilidadeVinculacaoCartaoRequest consultaDisponibilidadeVinculacaoCartao);

    @RequestLine("GET")
    @RequestMapping(method = RequestMethod.GET, value = "/cartoes/{id}")
    public ResultadoConsultaCartaoResponse informacoesCartao(@PathVariable("id") String id);

    @RequestLine("POST")
    @RequestMapping(method = RequestMethod.POST, value = "/cartoes/{id}/bloqueios")
    public BloqueioCartaoResultadoResponse bloquearCartao(@PathVariable("id") String id, @RequestBody BloquearCartaoRequest bloquearCartaoRequest);

    @RequestLine("POST")
    @RequestMapping(method = RequestMethod.POST, value = "/cartoes/{id}/avisos")
    public SolicitacaoAvisoViagemResponse avisoViagem(@PathVariable("id") String id, @RequestBody SolicitacaoAvisoViagemRequest solicitacaoAvisoViagemRequest);

}
