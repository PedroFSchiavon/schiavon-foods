package br.com.schiavon.food.domain.repositories;

import br.com.schiavon.food.domain.models.Produto;
import br.com.schiavon.food.domain.models.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByRestaurante(Restaurante restaurante);

    List<Produto> findByRestauranteAndAtivoIsTrue(Restaurante restaurante);

    Optional<Produto> findByRestaurante_IdAndId(Long restauranteId, Long produtoId);
}
