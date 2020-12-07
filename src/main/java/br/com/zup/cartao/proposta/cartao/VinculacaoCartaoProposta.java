package br.com.zup.cartao.proposta.cartao;

import br.com.zup.cartao.proposta.compartilhado.cartao.CartaoClient;
import br.com.zup.cartao.proposta.compartilhado.cartao.ConsultaDisponibilidadeVinculacaoCartaoRequest;
import br.com.zup.cartao.proposta.compartilhado.cartao.ResultadoConsultaCartaoResponse;
import br.com.zup.cartao.proposta.proposta.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@EnableAsync
@Component
public class VinculacaoCartaoProposta {

    private final Logger logger = LoggerFactory.getLogger(VinculacaoCartaoProposta.class);

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void verificaVinculacaoCartaoAPropostasElegiveis() {
        logger.info("iniciando checagem de novas propostas elegiveis...");

        List<Proposta> propostasDisponiveis = propostaRepository
                .findByPropostaElegibilidadeAndCartao(PropostaElegibilidade.ELEGIVEL, null);

        propostasDisponiveis.stream()
                .forEach(proposta -> {
                    logger.info("documento em processamento atualmente: "+proposta.getDocumento() + " -> "+ LocalDateTime.now());
                    realizaConsulta(proposta);
                });
    }

    public void realizaConsulta(Proposta proposta) {
        ConsultaDisponibilidadeVinculacaoCartaoRequest consultaDisponibilidadeVinculacaoCartaoRequest =
                new ConsultaDisponibilidadeVinculacaoCartaoRequest(
                        proposta.getDocumento(),
                        proposta.getNome(),
                        proposta.getId().toString()
                );

        ResponseEntity responseEntity =
                cartaoClient.consultaDisponibilidade(consultaDisponibilidadeVinculacaoCartaoRequest);

        ResultadoConsultaCartaoResponse resultadoConsultaCartaoResponse = retornoConsultaCartao(responseEntity);

        if(resultadoConsultaCartaoResponse != null) {
            realizaOperacaoVinculacao(resultadoConsultaCartaoResponse, proposta);
        }
    }

    public void realizaOperacaoVinculacao(ResultadoConsultaCartaoResponse resultadoConsultaCartaoResponse, Proposta proposta) {
        logger.info("há um cartão disponivel para vinculação...");
        Cartao cartao = buildCartao(resultadoConsultaCartaoResponse);
        proposta.setCartao(cartao);
        proposta.setPropostaSituacao(PropostaSituacao.APROVADA);
        logger.info("cartão em etapa de vinculação com a proposta");
        propostaRepository.save(proposta);
        logger.info("cartão vinculado com sucesso!");
    }
    
    public ResultadoConsultaCartaoResponse retornoConsultaCartao(ResponseEntity responseEntity) {

        ResultadoConsultaCartaoResponse resultadoConsultaCartaoResponse = null;

        if(responseEntity.getStatusCode().is2xxSuccessful()) {

            if(responseEntity.getHeaders().containsKey("Location")) {
                HttpHeaders httpHeaders = responseEntity.getHeaders();
                logger.info("cartão solicitado com sucesso!");
                String location = httpHeaders.get("Location").get(0);
                String id = location.substring(location.lastIndexOf("/") + 1);

                resultadoConsultaCartaoResponse = cartaoClient.informacoesCartao(id);
            }
        }
        return resultadoConsultaCartaoResponse;
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
