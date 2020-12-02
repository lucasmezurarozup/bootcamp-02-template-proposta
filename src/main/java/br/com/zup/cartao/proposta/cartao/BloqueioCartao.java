package br.com.zup.cartao.proposta.cartao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "bloqueios_cartao"
)
public class BloqueioCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroBloqueio;
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    private boolean ativo;

    @Deprecated
    private BloqueioCartao() {

    }
}
