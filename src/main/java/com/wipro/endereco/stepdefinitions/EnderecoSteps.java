package com.wipro.endereco.stepdefinitions;

import com.wipro.endereco.model.ConsultaEnderecoResponse;
import com.wipro.endereco.service.EnderecoService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import jakarta.inject.Singleton;


@Singleton
public class EnderecoSteps {

    private String cepValido;
    private final EnderecoService enderecoService;
    private ConsultaEnderecoResponse enderecoResponse;

    public EnderecoSteps(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @Given("um CEP válido {string}")
    public void cepValido(String cep) {
        cepValido = cep;
    }

    @When("eu consulto o endereço")
    public void consultaEndereco() {
        enderecoResponse = enderecoService.buscarEndereco(cepValido);
    }

    @Then("o endereço é retornado com sucesso")
    public void enderecoRetornadoComSucesso() {
        assertNotNull(enderecoResponse, "Endereço não encontrado");
        assertEquals(cepValido, enderecoResponse.getCep(), "O CEP retornado não corresponde ao esperado");
    }
}

