package br.com.zup.cartao.proposta.cartao;

import br.com.zup.cartao.proposta.proposta.Proposta;
import br.com.zup.cartao.proposta.proposta.PropostaElegibilidade;
import br.com.zup.cartao.proposta.proposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@EnableAsync
@Component
public class VinculacaoCartaoProposta {

    @Autowired
    private PropostaRepository propostaRepository;

    @Scheduled(fixedDelay = 500)
    public void verificaVinculacaoCartaoAPropostasElegiveis() {
        System.out.println("Iniciando...");
        List<Proposta> propostasDisponiveis = propostaRepository.findByPropostaElegibilidadeAndCartao(PropostaElegibilidade.ELEGIVEL, null);
        propostasDisponiveis.stream().forEach(proposta -> System.out.println(proposta.getDocumento() + " -> "+ LocalDateTime.now()));
        System.out.println("Acabou...");
    }
}
