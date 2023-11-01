package br.com.schiavon.food.infrastructure.repositorys;

import br.com.schiavon.food.domain.models.Pedido;
import br.com.schiavon.food.domain.repositories.PedidoRepositoryQuery;
import br.com.schiavon.food.domain.repositories.filter.PedidoFilter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PedidoRepositoryImpl implements PedidoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public Page<Pedido> findByClienteRestauranteData(PedidoFilter pedidoFilter, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteria = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteria.from(Pedido.class);

        root.fetch("restaurante").fetch("cozinha");
        root.fetch("cliente");

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

        //Verificando se foi passado parametro de ordenação.
        if (pageable.getSort().get().map(Sort.Order::getProperty).findFirst().isPresent()){
            String filtroOrdenacao = pageable.getSort().get().map(Sort.Order::getProperty).findFirst().get();
            System.out.println(filtroOrdenacao);
            Optional<Sort.Direction> directionOptional = pageable.getSort().get().map(Sort.Order::getDirection).findFirst();
            if (directionOptional.get().isAscending()){
                criteria.orderBy(criteriaBuilder.asc(root.get(filtroOrdenacao)));
            } else if (directionOptional.get().isDescending()) {
                criteria.orderBy(criteriaBuilder.desc(root.get(filtroOrdenacao)));
            }
        }

        List<Pedido> pedidoList = manager.createQuery(criteria).setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize()).getResultList();

        CriteriaQuery<Long> contadorQuery = criteriaBuilder.createQuery(Long.class);
        Root<Pedido> pedidoRootContador = contadorQuery.from(Pedido.class);
        contadorQuery.select(criteriaBuilder.count(pedidoRootContador)).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        Long contador = manager.createQuery(contadorQuery).getSingleResult();

        PageImpl<Pedido> pedidoPage = new PageImpl<>(pedidoList, pageable, contador);

        return pedidoPage;
    }
}
