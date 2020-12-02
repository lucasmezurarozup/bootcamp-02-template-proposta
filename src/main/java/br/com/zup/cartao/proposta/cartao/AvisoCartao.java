package br.com.zup.cartao.proposta.cartao;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
        name = "avisos_cartao"
)
public class AvisoCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate validoAte;
    private String destino;

    @Deprecated
    private AvisoCartao() {

    }
}
