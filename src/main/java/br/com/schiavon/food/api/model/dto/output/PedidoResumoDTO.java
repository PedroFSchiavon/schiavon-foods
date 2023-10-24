package br.com.schiavon.food.api.model.dto.output;

import br.com.schiavon.food.domain.models.enuns.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class PedidoResumoDTO {
    private String codigo;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private OffsetDateTime dataCriacao;
    private StatusPedido statusPedido;
    private UsuarioDTO cliente;
    private RestauranteResumidoDTO restaurante;
}
