package br.com.schiavon.food.api.model.dto.input;

import br.com.schiavon.food.core.validation.anotations.TaxaFrete;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RestauranteInputDTO {
    @NotBlank
    private String nome;

    @TaxaFrete
    @NotNull
    private BigDecimal taxaFrete;

    @Valid
    @NotNull
    CozinhaIDInput cozinha;
}
