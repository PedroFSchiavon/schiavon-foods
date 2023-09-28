package br.com.schiavon.food.api.model.dto.output;

import br.com.schiavon.food.api.model.dto.output.EnderecoDTO;
import br.com.schiavon.food.api.model.dto.output.FormaPagamentoDTO;
import br.com.schiavon.food.api.model.dto.output.ItemPedidoDTO;
import br.com.schiavon.food.api.model.dto.output.RestauranteResumidoDTO;
import br.com.schiavon.food.api.model.dto.output.UsuarioDTO;
import br.com.schiavon.food.domain.models.enuns.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PedidoDTO {
    private long id;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConfirmacao;
    private LocalDateTime dataCancelamento;
    private LocalDateTime dataEntrega;
    private StatusPedido statusPedido;
    private EnderecoDTO enderecoEntrega;
    private UsuarioDTO cliente;
    private RestauranteResumidoDTO restaurante;
    private FormaPagamentoDTO formaPagamento;
    private List<ItemPedidoDTO> itens;
}
