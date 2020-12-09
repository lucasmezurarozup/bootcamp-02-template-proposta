package br.com.zup.cartao.proposta.avisos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitacaoAvisoViagemRepository extends JpaRepository<SolicitacaoAvisoViagem, Long> {
}
