package com.wipro.endereco.service;

import com.wipro.endereco.service.exception.CepInvalidoException;
import com.wipro.endereco.model.ConsultaEnderecoResponse;
import com.wipro.endereco.model.ViaCepResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.wipro.endereco.service.util.FreteUtil.calcularFrete;

@Service
@AllArgsConstructor
public class EnderecoService {

    private RestTemplate restTemplate;

    private static final String VIACEP_JSON = "https://viacep.com.br/ws/%s/json/";

    public ConsultaEnderecoResponse buscarEndereco(String cep) {
        if (cepInvalido(cep)) {
            throw new CepInvalidoException("CEP inválido: " + cep);
        }
        var url = String.format(VIACEP_JSON, cep.replaceAll("\\D", ""));
        var viaCepResponse = restTemplate.getForObject(url, ViaCepResponse.class);

        if (viaCepResponse == null ||
                viaCepResponse.getCep() == null
        ) {
            return null;
        }

        var response = new ConsultaEnderecoResponse();
        response.setCep(viaCepResponse.getCep());
        response.setRua(viaCepResponse.getLogradouro());
        response.setComplemento(viaCepResponse.getComplemento());
        response.setBairro(viaCepResponse.getBairro());
        response.setCidade(viaCepResponse.getLocalidade());
        response.setEstado(viaCepResponse.getUf());
        response.setFrete(calcularFrete(viaCepResponse.getUf()));

        return response;
    }

    private boolean cepInvalido(String cep) {
        String cepSemFormatacao = cep.replaceAll("\\D", "");
        return cepSemFormatacao.length() != 8;
    }
}




