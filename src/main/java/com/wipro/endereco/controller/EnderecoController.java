package com.wipro.endereco.controller;

import com.wipro.endereco.model.CepRequest;
import com.wipro.endereco.model.ConsultaEnderecoResponse;
import com.wipro.endereco.service.EnderecoService;
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

    @PostMapping("/consulta-endereco")
    public ResponseEntity<ConsultaEnderecoResponse> consultarEndereco(@RequestBody CepRequest cepRequest) {
        var endereco = service.buscarEndereco(cepRequest.getCep());
        return Optional.ofNullable(endereco)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
