package br.com.schiavon.food.domain.repositories;

import br.com.schiavon.food.domain.models.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQuery {
    List<Restaurante> buscaPorNomeETaxa(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);

    List<Restaurante> buscarFreteGratis(String nome);
}
