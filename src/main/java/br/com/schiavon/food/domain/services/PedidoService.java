package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.naoencontrada.PedidoNaoEncontradaException;
import br.com.schiavon.food.domain.models.Pedido;
import br.com.schiavon.food.domain.repositories.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido buscarPedidoId(Long id){
        return pedidoRepository.findById(id).orElseThrow(() -> new PedidoNaoEncontradaException(id));
    }
}
