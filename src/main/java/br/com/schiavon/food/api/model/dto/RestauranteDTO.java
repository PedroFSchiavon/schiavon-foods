package br.com.schiavon.food.api.model.dto;

import java.math.BigDecimal;

public class RestauranteDTO {
    private long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaDTO cozinhaDTO;
}
