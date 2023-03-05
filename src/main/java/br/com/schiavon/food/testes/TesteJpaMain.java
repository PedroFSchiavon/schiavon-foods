package br.com.schiavon.food.testes;

import br.com.schiavon.food.domain.models.Cozinha;
import br.com.schiavon.food.domain.models.Restaurante;
import br.com.schiavon.food.domain.repositories.CozinhaRepository;
import br.com.schiavon.food.domain.repositories.RestauranteRepository;

import java.util.List;

public class TesteJpaMain {
    public static void main(String[] args) {
        RestauranteRepository restauranteRepository = GeradorRestauranteRepository.getCozinhaRepository();

        List<Restaurante> restaurantes = restauranteRepository.findAll();
        restaurantes.forEach(restaurante -> System.out.printf("----------------------------\n" +
                "|%s  |%s  |%s  |\n", restaurante.getNome(), restaurante.getTaxaFrete()
                .toString(), restaurante.getCozinha().getNome()));
    }
}
