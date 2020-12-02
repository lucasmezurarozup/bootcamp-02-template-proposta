package br.com.zup.cartao.proposta.cartao;

import br.com.zup.cartao.proposta.compartilhado.cartao.CartaoClient;
import br.com.zup.cartao.proposta.compartilhado.cartao.ConsultaDisponibilidadeVinculacaoCartaoRequest;
import br.com.zup.cartao.proposta.compartilhado.cartao.ResultadoConsultaCartaoResponse;
import br.com.zup.cartao.proposta.proposta.*;
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

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void verificaVinculacaoCartaoAPropostasElegiveis() {
        System.out.println("Iniciando...");

        List<Proposta> propostasDisponiveis = propostaRepository
                .findByPropostaElegibilidadeAndCartao(PropostaElegibilidade.ELEGIVEL, null);
        propostasDisponiveis.stream()
                .forEach(proposta -> {
                    System.out.println(proposta.getDocumento() + " -> "+ LocalDateTime.now());

                    ConsultaDisponibilidadeVinculacaoCartaoRequest consultaDisponibilidadeVinculacaoCartaoRequest =
                            new ConsultaDisponibilidadeVinculacaoCartaoRequest(proposta.getDocumento(), proposta.getNome(), proposta.getId().toString());

                    ResponseEntity responseEntity =
                            cartaoClient.consultaDisponibilidade(consultaDisponibilidadeVinculacaoCartaoRequest);

                    ResultadoConsultaCartaoResponse resultadoConsultaCartaoResponse = retornoConsultaCartao(responseEntity);

                    if(resultadoConsultaCartaoResponse != null) {
                        Cartao cartao = buildCartao(resultadoConsultaCartaoResponse);
                        proposta.setCartao(cartao);
                        proposta.setPropostaSituacao(PropostaSituacao.APROVADA);
                        propostaRepository.save(proposta);
                    }
                });
    }
    
    public ResultadoConsultaCartaoResponse retornoConsultaCartao(ResponseEntity responseEntity) {

        ResultadoConsultaCartaoResponse resultadoConsultaCartaoResponse = null;

        if(responseEntity.getStatusCode().is2xxSuccessful()) {

            if(responseEntity.getHeaders().containsKey("Location")) {
                HttpHeaders httpHeaders = responseEntity.getHeaders();

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


        System.out.println("numeroCartao: "+ cartao.getNumeroCartao());
        System.out.println("titular: "+ cartao.getTitular());
        System.out.println("limite: "+ cartao.getLimite());
        System.out.println("EmitidoEm: "+ cartao.getEmitidoEm());
        System.out.println("idProposta: "+cartao.getIdProposta());
        System.out.println("Bloqueios");
        cartao.getBloqueios().stream().forEach(bloqueioCartao -> System.out.println(bloqueioCartao.getId() + " - "+ bloqueioCartao.getBloqueadoEm()+ " - "+ bloqueioCartao.getSistemaResponsavel() + " - "+ bloqueioCartao.isAtivo()));
        System.out.println("Avisos");
        cartao.getAvisos().stream().forEach(avisoCartao -> System.out.println( " - "+ avisoCartao.getValidoAte() + " - "+ avisoCartao.getDestino()));
        System.out.println("Carteiras");
        cartao.getCarteiras().stream().forEach(carteiraCartao -> System.out.println( " "+ carteiraCartao.getEmail()+ " "+ carteiraCartao.getAssociadaEm() + " "+ carteiraCartao.getEmissor()));
        System.out.println("Parcelas");
        cartao.getParcelas().stream().forEach(parcelaCartao -> System.out.println(parcelaCartao.getId() + " "+ parcelaCartao.getNumeroParcela() + " "+ parcelaCartao.getQuantidade() + " "+ parcelaCartao.getValor()));
        System.out.println("Renegociacao");
        if(cartao.getRenegociacao() != null) {
            System.out.println(cartao.getRenegociacao().getNumeroRenegociacao() + " " + cartao.getRenegociacao().getQuantidade() + " " + cartao.getRenegociacao().getValor());
        }
        System.out.println("Vencimento");
        System.out.println(cartao.getVencimento().getNumeroVencimento() + " " + cartao.getVencimento().getDia() + " "+ cartao.getVencimento().getDataCriacao());
        return cartao;
    }
}
