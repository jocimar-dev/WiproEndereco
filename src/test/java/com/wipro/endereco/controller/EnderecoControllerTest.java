package com.wipro.endereco.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.endereco.model.CepRequest;
import com.wipro.endereco.service.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.wipro.endereco.MockFactory.payloadoRequestMock;
import static com.wipro.endereco.MockFactory.payloadoResponseMock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EnderecoControllerTest {

    @Autowired(required = false)
    private  MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EnderecoService service;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        payloadoRequestMock();
    }

    @Test
    @DisplayName("Deve retornar 200 quando o CEP for encontrado")
    void deveRetornar200QuandoCepEncontrado() throws Exception {
        when(service.buscarEndereco(any())).thenReturn(payloadoResponseMock());

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
    @DisplayName("Deve retornar 404 quando o CEP não for encontrado")
    void retornaNotFound() throws Exception {
        when(service.buscarEndereco(any())).thenReturn(null);

        mockMvc.perform(post("/v1/consulta-endereco")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CepRequest("12345678"))))
                .andExpect(status().isNotFound());
    }
}
