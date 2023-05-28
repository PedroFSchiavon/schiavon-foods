package br.com.schiavon.food.infrastructure.repositorys;

import br.com.schiavon.food.domain.models.Restaurante;
import br.com.schiavon.food.domain.repositories.RestauranteRepository;
import br.com.schiavon.food.domain.repositories.RestauranteRepositoryQuery;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static br.com.schiavon.food.infrastructure.specifications.RestauranteSpecificationFactory.comFreteGratis;
import static br.com.schiavon.food.infrastructure.specifications.RestauranteSpecificationFactory.comNomeSemelhante;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQuery {
    @PersistenceContext
    private EntityManager manager;
    private final RestauranteRepository restauranteRepository;

    public RestauranteRepositoryImpl(@Lazy RestauranteRepository restauranteRepository){
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public List<Restaurante> buscaPorNomeETaxa(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal){
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Restaurante> criteria = criteriaBuilder.createQuery(Restaurante.class);

        Root<Restaurante> root = criteria.from(Restaurante.class);

        ArrayList<Predicate> predicates = new ArrayList<>();

        if(nome != null && !nome.isBlank())
            predicates.add(criteriaBuilder.like(root.get("nome"), "%" + nome + "%"));
        if (taxaInicial != null)
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaInicial));
        if (taxaFinal != null)
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFinal));

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurante> query = manager.createQuery(criteria);

        return query.getResultList();

        //QUERY DINAMICA COM JPQL
        /*StringBuilder jpql = new StringBuilder("from Restaurante as r where 1=1 ");
        HashMap<String, Object> parametros = new HashMap<>();

        if (nome != null && !nome.isBlank()){
            System.out.println("Passei aqui: " + nome);
            jpql.append("and r.nome like :nome ");
            parametros.put("nome", "%" + nome + "%");
        }
        if (taxaInicial != null){
            jpql.append("and r.taxaFrete >= :taxaInicial ");
            parametros.put("taxaInicial", taxaInicial);
        }
        if (taxaFinal != null){
            jpql.append("and r.taxaFrete <= :taxaFinal ");
            parametros.put("taxaFinal", taxaFinal);
        }

        TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);

        parametros.forEach(query::setParameter);

        return query.getResultList();*/
    }

    @Override
    public List<Restaurante> buscarFreteGratis(String nome) {
        return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
    }
}
