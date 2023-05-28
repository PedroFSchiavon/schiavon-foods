package br.com.schiavon.food.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface CustomJpaRepositry<T, ID> extends JpaRepository<T, ID> {
    public Optional<T> bucarPrimeiro();
}
