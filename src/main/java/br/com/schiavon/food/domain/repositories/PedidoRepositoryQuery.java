package br.com.schiavon.food.domain.repositories;

import br.com.schiavon.food.domain.models.Pedido;
import br.com.schiavon.food.domain.repositories.filter.PedidoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoRepositoryQuery {
    Page<Pedido> findByClienteRestauranteData(PedidoFilter pedidoFilter, Pageable pageable);
}
