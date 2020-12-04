package br.com.zup.cartao.proposta.proposta;

import br.com.zup.cartao.proposta.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    List<Proposta> findByPropostaElegibilidadeAndCartao(PropostaElegibilidade propostaElegibilidade, Cartao cartao);
    Optional<Proposta> findByCartaoNumeroCartao(String numeroCartao);
}
