package br.com.schiavon.food.domain.repositories;

import br.com.schiavon.food.domain.models.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}
