package com.wipro.endereco.service.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class FreteUtil {

    private static final Map<String, Double> FRETE_POR_ESTADO = inicializarValoresFrete();

    private static Map<String, Double> inicializarValoresFrete() {
        Map<String, Double> fretePorEstado = new HashMap<>();

        // Sudeste
        fretePorEstado.put("SP", 7.85);
        fretePorEstado.put("RJ", 7.85);
        fretePorEstado.put("ES", 7.85);
        fretePorEstado.put("MG", 7.85);

        // Centro-Oeste
        fretePorEstado.put("DF", 12.50);
        fretePorEstado.put("GO", 12.50);
        fretePorEstado.put("MT", 12.50);
        fretePorEstado.put("MS", 12.50);

        // Nordeste
        fretePorEstado.put("AL", 15.98);
        fretePorEstado.put("BA", 15.98);
        fretePorEstado.put("CE", 15.98);
        fretePorEstado.put("MA", 15.98);
        fretePorEstado.put("PB", 15.98);
        fretePorEstado.put("PE", 15.98);
        fretePorEstado.put("PI", 15.98);
        fretePorEstado.put("RN", 15.98);
        fretePorEstado.put("SE", 15.98);

        // Sul
        fretePorEstado.put("PR", 17.30);
        fretePorEstado.put("RS", 17.30);
        fretePorEstado.put("SC", 17.30);

        // Norte
        fretePorEstado.put("AC", 20.83);
        fretePorEstado.put("AM", 20.83);
        fretePorEstado.put("AP", 20.83);
        fretePorEstado.put("PA", 20.83);
        fretePorEstado.put("RO", 20.83);
        fretePorEstado.put("RR", 20.83);
        fretePorEstado.put("TO", 20.83);

        return fretePorEstado;
    }


    public static double calcularFrete(String estado) {
        return FRETE_POR_ESTADO.getOrDefault(estado, 0.0);
    }
}
