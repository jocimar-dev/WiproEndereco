package com.wipro.endereco.service;

import com.wipro.endereco.model.ViaCepResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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
}
