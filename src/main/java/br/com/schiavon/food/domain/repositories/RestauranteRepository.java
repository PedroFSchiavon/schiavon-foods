package br.com.schiavon.food.domain.repositories;

import br.com.schiavon.food.domain.models.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
