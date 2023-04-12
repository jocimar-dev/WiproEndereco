package com.wipro.endereco.controller;

import com.wipro.endereco.dto.CepDto;
import com.wipro.endereco.model.Endereco;
import com.wipro.endereco.service.EnderecoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/v1")
public class EnderecoController {

    private EnderecoService enderecoService;

    @PostMapping("/consulta-endereco")
    public ResponseEntity<Endereco> consultarEndereco(@RequestBody CepDto cepDto) {
        Endereco endereco = enderecoService.buscarEndereco(cepDto.getCep());
        if (endereco == null) {
            return ResponseEntity.notFound().build();
        }
        calcularFrete(endereco);
        return ResponseEntity.ok(endereco);
    }

    private void calcularFrete(Endereco endereco) {
        String estado = endereco.getEstado();
        switch (estado.toUpperCase()) {
            case "SP":
            case "RJ":
            case "ES":
            case "MG":
                endereco.setFrete(7.85);
                break;
            case "DF":
            case "GO":
            case "MT":
            case "MS":
                endereco.setFrete(12.50);
                break;
            case "AL":
            case "BA":
            case "CE":
            case "MA":
            case "PB":
            case "PE":
            case "PI":
            case "RN":
            case "SE":
                endereco.setFrete(15.98);
                break;
            case "PR":
            case "RS":
            case "SC":
                endereco.setFrete(17.30);
                break;
            case "AC":
            case "AP":
            case "AM":
            case "PA":
            case "RO":
            case "RR":
            case "TO":
                endereco.setFrete(20.83);
                break;
            default:
                endereco.setFrete(0.0);
                break;
        }
    }

}
