package com.wipro.endereco.service;

import com.wipro.endereco.controller.EnderecoController;
import com.wipro.endereco.model.ViaCepResponse;
import com.wipro.endereco.service.exception.CepInvalidoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EnderecoServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private EnderecoService enderecoService;

    @Test
    @DisplayName("Deve buscar endereço válido")
    void deveBuscarEndereco() {
        var viaCepResponse = new ViaCepResponse();

        viaCepResponse.setCep("01001-000");
        viaCepResponse.setLogradouro("Praça da Sé");
        viaCepResponse.setComplemento("lado ímpar");
        viaCepResponse.setBairro("Sé");
        viaCepResponse.setLocalidade("São Paulo");
        viaCepResponse.setUf("SP");

        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(viaCepResponse);

        var expectedResponse = enderecoService.buscarEndereco("01001-000");

        assertNotNull(expectedResponse);
        assertEquals(viaCepResponse.getCep(), expectedResponse.getCep());
        assertEquals(viaCepResponse.getLogradouro(), expectedResponse.getRua());
        assertEquals(viaCepResponse.getComplemento(), expectedResponse.getComplemento());
        assertEquals(viaCepResponse.getBairro(), expectedResponse.getBairro());
        assertEquals(viaCepResponse.getLocalidade(), expectedResponse.getCidade());
        assertEquals(viaCepResponse.getUf(), expectedResponse.getEstado());
        assertEquals(7.85, expectedResponse.getFrete());
    }

    @Test
    @DisplayName("Deve retornar endereço null")
    void deveRetornarEnderecoNull() {
        when(restTemplate.getForObject(anyString(), any(Class.class))).thenReturn(null);
        var response = enderecoService.buscarEndereco("00000-000");
        assertNull(response);
    }

    @Test
    @DisplayName("Deve lançar exceção de CEP inválido")
    void deveLancarExcecaoDeCepInvalido() {
        var cepInvalido = "00000-0000";

        var excecao = assertThrows(CepInvalidoException.class, () ->
                enderecoService.buscarEndereco(cepInvalido));
        assertEquals("CEP inválido: " + cepInvalido, excecao.getMessage());

    }

    @Test
    @DisplayName("Deve lançar exceção de CEP inválido e retornar 404")
    void deveLancarExcecaoDeCepInvalidoERetornar404() throws Exception {
        var cepInvalido = "00000-0000";
        var mockMvc = MockMvcBuilders.standaloneSetup(new EnderecoController(enderecoService)).build();

        mockMvc.perform(get("/api/consulta-cep/{cep}", cepInvalido)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
