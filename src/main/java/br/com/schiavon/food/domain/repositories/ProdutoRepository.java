package br.com.schiavon.food.domain.repositories;

import br.com.schiavon.food.domain.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
