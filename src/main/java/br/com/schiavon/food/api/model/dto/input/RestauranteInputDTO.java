package br.com.schiavon.food.api.model.dto.input;

import br.com.schiavon.food.core.validation.anotations.TaxaFrete;
import br.com.schiavon.food.core.validation.anotations.ValorZeroIncluiDescricao;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoField = "nome", descricao = "Frete Grátis")
public class RestauranteInputDTO {
    @NotBlank
    private String nome;

    @TaxaFrete
    @NotNull
    private BigDecimal taxaFrete;

    @Valid
    @NotNull
    CozinhaIDInput cozinha;

    @Valid
    @NotNull
    EnderecoInputDTO endereco;
}
