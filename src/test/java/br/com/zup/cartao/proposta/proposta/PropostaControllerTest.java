package br.com.zup.cartao.proposta.proposta;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PropostaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private NovaPropostaRequest novaPropostaRequest;

    @Test
    public void testandoExistenciaRotaPropostaSemCamposPreenchidos() throws Exception {
        mockMvc.perform(post("/propostas/nova")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRegistrarComDocumentoNulo() throws Exception {
        NovaPropostaRequest novaPropostaRequest = new NovaPropostaRequest(null, "lucas@zup.com.br", "lucas", "rua 2", BigDecimal.valueOf(1000.00));

        mockMvc.perform(post("/propostas/nova")
                .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(novaPropostaRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRegistrarComEmailNulo() throws Exception {
        NovaPropostaRequest novaPropostaRequest = new NovaPropostaRequest("516.170.390-39", null, "lucas", "rua 2", BigDecimal.valueOf(1000.00));

        mockMvc.perform(post("/propostas/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaPropostaRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRegistrarComNomeNulo() throws Exception {
        NovaPropostaRequest novaPropostaRequest = new NovaPropostaRequest("516.170.390-39", "lucas@zup.com.br", null, "rua 2", BigDecimal.valueOf(1000.00));

        mockMvc.perform(post("/propostas/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaPropostaRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRegistrarComEnderecoNulo() throws Exception {
        NovaPropostaRequest novaPropostaRequest = new NovaPropostaRequest("516.170.390-39", "lucas@zup.com.br", "lucas", null, BigDecimal.valueOf(1000.00));

        mockMvc.perform(post("/propostas/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaPropostaRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRegistrarComSalarioNulo() throws Exception {
        NovaPropostaRequest novaPropostaRequest = new NovaPropostaRequest("516.170.390-39", "lucas@zup.com.br", "lucas", "rua 2",null);

        mockMvc.perform(post("/propostas/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaPropostaRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRegistrarComSalarioZerado() throws Exception {
        NovaPropostaRequest novaPropostaRequest = new NovaPropostaRequest("516.170.390-39", "lucas@zup.com.br", "lucas", "rua 2",BigDecimal.valueOf(0));

        mockMvc.perform(post("/propostas/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaPropostaRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRegistrarComSalarioNegativo() throws Exception {
        NovaPropostaRequest novaPropostaRequest = new NovaPropostaRequest("516.170.390-39", "lucas@zup.com.br", "lucas", "rua 2",BigDecimal.valueOf(-1));

        mockMvc.perform(post("/propostas/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaPropostaRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testandoRegistrarComDocumentoInvalido() throws Exception {
        NovaPropostaRequest novaPropostaRequest = new NovaPropostaRequest("500.170.390", "lucas@zup.com.br", "lucas", "rua 2",BigDecimal.valueOf(-1));

        mockMvc.perform(post("/propostas/nova")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(novaPropostaRequest)))
                .andExpect(status().isBadRequest());
    }
}
