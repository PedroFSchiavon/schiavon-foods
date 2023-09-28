package br.com.schiavon.food.api.model.dto.output;

import br.com.schiavon.food.api.model.dto.output.RestauranteResumidoDTO;
import br.com.schiavon.food.api.model.dto.output.UsuarioDTO;
import br.com.schiavon.food.domain.models.enuns.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PedidoResumoDTO {
    private long id;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private LocalDateTime dataCriacao;
    private StatusPedido statusPedido;
    private UsuarioDTO cliente;
    private RestauranteResumidoDTO restaurante;
}
