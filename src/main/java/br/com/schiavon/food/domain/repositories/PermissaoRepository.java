package br.com.schiavon.food.domain.repositories;

import br.com.schiavon.food.domain.models.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}
