package com.wipro.endereco.service;

import com.wipro.endereco.model.Endereco;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class EnderecoService {

    private final String VIA_CEP_URL = "https://viacep.com.br/ws/";

    private RestTemplate restTemplate;

    public Endereco buscarEndereco(String cep) {
        String url = VIA_CEP_URL + cep + "/json/";
        return restTemplate.getForObject(url, Endereco.class);
    }

}
