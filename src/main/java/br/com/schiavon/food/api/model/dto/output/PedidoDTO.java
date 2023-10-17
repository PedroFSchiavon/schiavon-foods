package br.com.schiavon.food.api.model.dto.output;

import br.com.schiavon.food.domain.models.enuns.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class PedidoDTO {
    private long id;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataEntrega;
    private StatusPedido statusPedido;
    private EnderecoDTO enderecoEntrega;
    private UsuarioDTO cliente;
    private RestauranteResumidoDTO restaurante;
    private FormaPagamentoDTO formaPagamento;
    private List<ItemPedidoDTO> itens;
}
