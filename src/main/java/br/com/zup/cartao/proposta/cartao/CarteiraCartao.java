package br.com.zup.cartao.proposta.cartao;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "carteiras_cartao"
)
public class CarteiraCartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroCarteira;
    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    @Deprecated
    private CarteiraCartao() {

    }

}
