package br.com.schiavon.food.api.model.dto.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInputDTO {
    @NotNull
    private Long produtoId;
    @NotNull
    @Positive
    private Integer quantidade;
    private String observacao;
}
