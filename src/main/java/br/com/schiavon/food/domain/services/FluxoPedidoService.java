package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.NegocioException;
import br.com.schiavon.food.domain.models.Pedido;
import br.com.schiavon.food.domain.models.enuns.StatusPedido;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class FluxoPedidoService {
    private final GeradorPedidoService geradorPedidoService;

    public FluxoPedidoService(GeradorPedidoService geradorPedidoService) {
        this.geradorPedidoService = geradorPedidoService;
    }

    @Transactional
    public void statusConfirmado(Long id){
        Pedido pedido = geradorPedidoService.buscarPedidoId(id);

        if (!(pedido.getStatusPedido() == StatusPedido.CRIADO)){
          throw new NegocioException(String.format("O pedido de id %d esta com o status \"%s\", não sendo " +
                  "possível alterar para o status \"%s\".", id, pedido.getStatusPedido(), StatusPedido.CONFIRMADO));
        }
        pedido.setDataConfirmacao(OffsetDateTime.now());
        pedido.setStatusPedido(StatusPedido.CONFIRMADO);
    }

    @Transactional
    public void statusEntregue(Long id){
        Pedido pedido = geradorPedidoService.buscarPedidoId(id);

        if (!(pedido.getStatusPedido() == StatusPedido.CONFIRMADO)){
            throw new NegocioException(String.format("O pedido de id %d esta com o status \"%s\", não sendo " +
                    "possível alterar para o status \"%s\".", id, pedido.getStatusPedido(), StatusPedido.ENTREGUE));
        }

        pedido.setDataEntrega(OffsetDateTime.now());
        pedido.setStatusPedido(StatusPedido.ENTREGUE);
    }

    @Transactional
    public void statusCancelado(Long id){
        Pedido pedido = geradorPedidoService.buscarPedidoId(id);

        if (!(pedido.getStatusPedido() == StatusPedido.CRIADO)){
            throw new NegocioException(String.format("O pedido de id %d esta com o status \"%s\", não sendo " +
                    "possível alterar para o status \"%s\".", id, pedido.getStatusPedido(), StatusPedido.CANCELADO));
        }

        pedido.setDataCancelamento(OffsetDateTime.now());
        pedido.setStatusPedido(StatusPedido.CANCELADO);
    }
}
