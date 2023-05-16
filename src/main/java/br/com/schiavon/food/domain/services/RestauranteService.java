package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.schiavon.food.domain.models.Cozinha;
import br.com.schiavon.food.domain.models.Restaurante;
import br.com.schiavon.food.domain.repositories.CozinhaRepository;
import br.com.schiavon.food.domain.repositories.RestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestauranteService {
    private final RestauranteRepository restauranteRepository;
    private final CozinhaRepository cozinhaRepository;

    public RestauranteService(RestauranteRepository restauranteRepository, CozinhaRepository cozinhaRepository){
        this.restauranteRepository = restauranteRepository;
        this.cozinhaRepository = cozinhaRepository;
    }

    public Restaurante cadastro(Restaurante restaurante){
        long idCozinha = restaurante.getCozinha().getId();
        Optional<Cozinha> cozinhaOptional = cozinhaRepository.findById(idCozinha);
        if(cozinhaOptional.isPresent()){
            restaurante.setCozinha(cozinhaOptional.get());
            return restauranteRepository.save(restaurante);
        }
        throw new EntidadeNaoEncontradaException(String.format("Cozinha de id %d não encontrada.", idCozinha));
    }
}
