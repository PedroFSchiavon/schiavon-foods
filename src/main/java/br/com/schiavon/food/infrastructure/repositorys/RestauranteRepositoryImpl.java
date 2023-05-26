package br.com.schiavon.food.infrastructure.repositorys;

import br.com.schiavon.food.domain.models.Restaurante;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl {
    @PersistenceContext
    private EntityManager manager;

    public List<Restaurante> buscaPorNomeETaxa(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal){
        String jpql = "from Restaurante as r where r.nome like :nome " +
                "and r.taxaFrete between :taxaInicial and :taxaFinal";

        return manager.createQuery(jpql, Restaurante.class).setParameter("nome", "%" + nome + "%")
                .setParameter("taxaInicial", taxaInicial)
                .setParameter("taxaFinal", taxaFinal)
                .getResultList();
    }
}
