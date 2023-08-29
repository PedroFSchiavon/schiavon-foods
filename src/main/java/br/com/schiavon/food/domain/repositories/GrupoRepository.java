package br.com.schiavon.food.domain.repositories;

import br.com.schiavon.food.domain.models.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}
