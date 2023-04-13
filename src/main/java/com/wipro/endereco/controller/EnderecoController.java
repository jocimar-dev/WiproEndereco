package com.wipro.endereco.controller;

import com.wipro.endereco.model.CepRequest;
import com.wipro.endereco.model.ConsultaEnderecoResponse;
import com.wipro.endereco.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/v1")
public class EnderecoController {

    private final EnderecoService service;

    @Operation(summary = "Consulta o endereço de acordo com CEP informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
    @PostMapping("/consulta-endereco")
    public ResponseEntity<ConsultaEnderecoResponse> consultarEndereco(
            @Parameter(description = "CEP do endereço a ser consultado  (ex: 12345678)", required = true)
            @RequestBody CepRequest cepRequest) {

        var endereco = service.buscarEndereco(cepRequest.getCep());
        return Optional.ofNullable(endereco)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
