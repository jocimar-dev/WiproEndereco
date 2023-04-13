package com.wipro.endereco;

import com.wipro.endereco.model.ConsultaEnderecoResponse;

public class MockFactory {

    private static final String cep = "12345678";
    private static final String rua = "Rua Teste";
    private static final String complemento = "Complemento Teste";
    private static final String bairro = "Bairro Teste";
    private static final String cidade = "Cidade Teste";
    private static final String estado = "SP";
    private static final double frete = 7.85;

    public static ConsultaEnderecoResponse payloadoRequestMock() {
        var enderecoResponse = new ConsultaEnderecoResponse();
        enderecoResponse.setCep(cep);
        enderecoResponse.setRua(rua);
        enderecoResponse.setComplemento(complemento);
        enderecoResponse.setBairro(bairro);
        enderecoResponse.setCidade(cidade);
        enderecoResponse.setEstado(estado);
        enderecoResponse.setFrete(frete);
        return enderecoResponse;
    }

    public static ConsultaEnderecoResponse payloadoResponseMock() {
        var enderecoResponse = new ConsultaEnderecoResponse();
        enderecoResponse.setCep(cep);
        enderecoResponse.setRua(rua);
        enderecoResponse.setComplemento(complemento);
        enderecoResponse.setBairro(bairro);
        enderecoResponse.setCidade(cidade);
        enderecoResponse.setEstado(estado);
        enderecoResponse.setFrete(frete);
        return enderecoResponse;
    }
}
