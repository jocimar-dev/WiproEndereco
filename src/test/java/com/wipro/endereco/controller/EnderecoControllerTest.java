package com.wipro.endereco.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.endereco.model.CepRequest;
import com.wipro.endereco.model.ConsultaEnderecoResponse;
import com.wipro.endereco.service.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EnderecoController.class)
class EnderecoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EnderecoService service;

    private ConsultaEnderecoResponse enderecoResponse;

    @BeforeEach
    void setUp() {
        enderecoResponse = new ConsultaEnderecoResponse();
        enderecoResponse.setCep("12345678");
        enderecoResponse.setRua("Rua Teste");
        enderecoResponse.setComplemento("Complemento Teste");
        enderecoResponse.setBairro("Bairro Teste");
        enderecoResponse.setCidade("Cidade Teste");
        enderecoResponse.setEstado("SP");
        enderecoResponse.setFrete(7.85);
    }

    @Test
    void consultarEndereco_ValidCep() throws Exception {
        when(service.buscarEndereco(any())).thenReturn(enderecoResponse);

        mockMvc.perform(post("/v1/consulta-endereco")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CepRequest("12345678"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cep").value("12345678"))
                .andExpect(jsonPath("$.rua").value("Rua Teste"))
                .andExpect(jsonPath("$.complemento").value("Complemento Teste"))
                .andExpect(jsonPath("$.bairro").value("Bairro Teste"))
                .andExpect(jsonPath("$.cidade").value("Cidade Teste"))
                .andExpect(jsonPath("$.estado").value("SP"))
                .andExpect(jsonPath("$.frete").value(7.85));
    }

    @Test
    void consultarEndereco_NotFound() throws Exception {
        when(service.buscarEndereco(any())).thenReturn(null);

        mockMvc.perform(post("/v1/consulta-endereco")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CepRequest("12345678"))))
                .andExpect(status().isNotFound());
    }
}
