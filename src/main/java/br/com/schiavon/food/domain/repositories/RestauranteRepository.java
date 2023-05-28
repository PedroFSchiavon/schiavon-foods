package br.com.schiavon.food.domain.repositories;

import br.com.schiavon.food.domain.models.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQuery,
        JpaSpecificationExecutor<Restaurante> {
    List<Restaurante> buscarPorNome(String nome);
}
