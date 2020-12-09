package br.com.zup.cartao.proposta.carteiras;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraDigitalRepository extends JpaRepository<CarteiraDigital, Long> {
}
