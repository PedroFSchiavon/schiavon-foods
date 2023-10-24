package br.com.schiavon.food.domain.repositories;

import br.com.schiavon.food.domain.models.Pedido;
import br.com.schiavon.food.domain.repositories.filter.PedidoFilter;

import java.util.List;

public interface PedidoRepositoryQuery {
    List<Pedido> findByClienteRestauranteData(PedidoFilter pedidoFilter);
}
