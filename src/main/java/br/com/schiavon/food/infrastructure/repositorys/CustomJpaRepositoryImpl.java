package br.com.schiavon.food.infrastructure.repositorys;

import br.com.schiavon.food.domain.repositories.CustomJpaRepositry;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepositry<T, ID> {
    private EntityManager manager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.manager = entityManager;
    }

    @Override
    public Optional<T> bucarPrimeiro() {
        String jpql = "from " + getDomainClass().getName();

        TypedQuery<T> query = manager.createQuery(jpql, getDomainClass()).setMaxResults(1);
        T singleResult = query.getSingleResult();
        return Optional.ofNullable(singleResult);
    }
}
