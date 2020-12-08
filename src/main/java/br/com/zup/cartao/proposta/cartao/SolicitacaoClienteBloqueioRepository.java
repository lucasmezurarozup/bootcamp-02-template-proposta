package br.com.zup.cartao.proposta.cartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoClienteBloqueioRepository extends JpaRepository<SolicitacaoClienteBloqueio, Long> {
}
