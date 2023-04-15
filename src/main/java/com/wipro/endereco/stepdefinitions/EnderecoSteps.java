package com.wipro.endereco.stepdefinitions;

import com.wipro.endereco.model.ConsultaEnderecoResponse;
import com.wipro.endereco.service.EnderecoService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.springframework.util.Assert;

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
        Assert.notNull(enderecoResponse, "Endereço não encontrado");
        Assert.isTrue(cepValido.equals(enderecoResponse.getCep()), "O CEP retornado não corresponde ao esperado");
    }

}

