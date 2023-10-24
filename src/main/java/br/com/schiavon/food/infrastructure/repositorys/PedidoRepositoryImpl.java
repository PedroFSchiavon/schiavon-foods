package br.com.schiavon.food.infrastructure.repositorys;

import br.com.schiavon.food.domain.models.Pedido;
import br.com.schiavon.food.domain.repositories.PedidoRepositoryQuery;
import br.com.schiavon.food.domain.repositories.filter.PedidoFilter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PedidoRepositoryImpl implements PedidoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Pedido> findByClienteRestauranteData(PedidoFilter pedidoFilter) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteria = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteria.from(Pedido.class);

        ArrayList<Predicate> predicates = new ArrayList<>();

        if (pedidoFilter.getClienteId() != null){
            predicates.add(criteriaBuilder.equal(root.get("cliente"), pedidoFilter.getClienteId()));
        }
        if (pedidoFilter.getRestauranteId() != null){
            predicates.add(criteriaBuilder.equal(root.get("restaurante"), pedidoFilter.getRestauranteId()));
        }
        if (pedidoFilter.getDataCriacaoInicio() != null){
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataCriacao"), pedidoFilter.getDataCriacaoInicio()));
        }
        if (pedidoFilter.getDataCriacaoFinal() != null){
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataCriacao"), pedidoFilter.getDataCriacaoFinal()));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Pedido> query = manager.createQuery(criteria);

        return query.getResultList();
    }
}
