package br.com.zup.cartao.proposta.cartao;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "vencimentos_cartao"
)
public class VencimentoCartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroVencimento;
    private int dia;
    private LocalDateTime dataCriacao;

    @Deprecated
    private VencimentoCartao() {

    }
}
