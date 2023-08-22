package br.com.schiavon.food.api.model.dto.output;

import br.com.schiavon.food.api.model.dto.output.CozinhaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class RestauranteDTO {
    private long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaDTO cozinha;
}