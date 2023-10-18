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
    public void statusConfirmado(Long id){
        Pedido pedido = geradorPedidoService.buscarPedidoId(id);

        pedido.confirma();
    }

    @Transactional
    public void statusEntregue(Long id){
        Pedido pedido = geradorPedidoService.buscarPedidoId(id);

        pedido.entregue();
    }

    @Transactional
    public void statusCancelado(Long id){
        Pedido pedido = geradorPedidoService.buscarPedidoId(id);

        pedido.cancelado();
    }
}
