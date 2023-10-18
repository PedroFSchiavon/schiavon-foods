package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.domain.services.FluxoPedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos/{codigoPedido}/status")
public class FluxoPedidoController {
    private final FluxoPedidoService fluxoPedidoService;

    public FluxoPedidoController(FluxoPedidoService fluxoPedidoService) {
        this.fluxoPedidoService = fluxoPedidoService;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/confirmado")
    public void confirmado(@PathVariable String codigoPedido){
        fluxoPedidoService.statusConfirmado(codigoPedido);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/entregue")
    public void entregue(@PathVariable String codigoPedido){
        fluxoPedidoService.statusEntregue(codigoPedido);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/cancelado")
    public void cancelado(@PathVariable String codigoPedido){
        fluxoPedidoService.statusCancelado(codigoPedido);
    }
}
