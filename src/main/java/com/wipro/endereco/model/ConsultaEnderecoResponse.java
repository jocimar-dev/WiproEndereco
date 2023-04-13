package com.wipro.endereco.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaEnderecoResponse {
    private String cep;
    private String rua;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    private double frete;

}
