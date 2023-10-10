package br.com.schiavon.food.api.model.dto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoInputDTO {
    @NotNull
    @Valid
    private RestauranteIdInput restaurante;
    @NotNull
    @Valid
    private FormaPagamentoIdInput formaPagamento;
    @NotNull
    @Valid
    private EnderecoInputDTO enderecoEntrega;
    @NotNull
    @Size(min = 1)
    @Valid
    private List<ItemPedidoInputDTO> itensPedidos;
}
