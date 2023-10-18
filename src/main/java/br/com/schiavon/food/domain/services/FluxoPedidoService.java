package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.models.Pedido;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoPedidoService {
    private final GeradorPedidoService geradorPedidoService;

    public FluxoPedidoService(GeradorPedidoService geradorPedidoService) {
        this.geradorPedidoService = geradorPedidoService;
    }

    @Transactional
    public void statusConfirmado(String codigo){
        Pedido pedido = geradorPedidoService.buscarPedidoId(codigo);

        pedido.confirma();
    }

    @Transactional
    public void statusEntregue(String codigo){
        Pedido pedido = geradorPedidoService.buscarPedidoId(codigo);

        pedido.entregue();
    }

    @Transactional
    public void statusCancelado(String codigo){
        Pedido pedido = geradorPedidoService.buscarPedidoId(codigo);

        pedido.cancelado();
    }
}
