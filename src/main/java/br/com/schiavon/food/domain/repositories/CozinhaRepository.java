package br.com.schiavon.food.domain.repositories;

import br.com.schiavon.food.domain.models.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
}
