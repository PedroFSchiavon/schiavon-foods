package br.com.schiavon.food.infrastructure.repositorys;

import br.com.schiavon.food.domain.models.Restaurante;
import br.com.schiavon.food.domain.repositories.RestauranteRepositoryQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQuery {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> buscaPorNomeETaxa(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal){
        StringBuilder jpql = new StringBuilder("from Restaurante as r where 1=1 ");
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

        parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

        return query.getResultList();
    }
}
