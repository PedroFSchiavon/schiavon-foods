package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.EntidadeEmUsoException;
import br.com.schiavon.food.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.schiavon.food.domain.exceptions.RelacionamentoEntidadeNaoEncontradoException;
import br.com.schiavon.food.domain.models.Cozinha;
import br.com.schiavon.food.domain.models.Restaurante;
import br.com.schiavon.food.domain.repositories.CozinhaRepository;
import br.com.schiavon.food.domain.repositories.RestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
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

    public Restaurante atualizar(Long id, Restaurante restaurante){
        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(id);
        long cozinhaId = restaurante.getCozinha().getId();
        Optional<Cozinha> cozinhaOptional = cozinhaRepository.findById(cozinhaId);
        if(restauranteOptional.isEmpty()){
            throw new EntidadeNaoEncontradaException(String.format("Restaurante com id %d não encontrado", id));
        }else if(cozinhaOptional.isEmpty()){
            throw new RelacionamentoEntidadeNaoEncontradoException(String
                    .format("Cozinha com id %d não encontrado", cozinhaId));
        }else {
            restaurante.setId(id);
            return restauranteRepository.save(restaurante);
        }
    }

    public void atualizarParcial(Restaurante restauranteDestino, Map<String, Object> dadosOrigem) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
        System.out.println(restauranteOrigem);

        dadosOrigem.forEach((chave, valor) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, chave);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }

    public void deletar(Long id){
        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(id);

        if(restauranteOptional.isPresent()){
            try {
                restauranteRepository.delete(restauranteOptional.get());
            }catch (DataIntegrityViolationException e){
                throw new EntidadeEmUsoException(String.format("Restaurante de id %d esta em uso por outra entidade", id));
            }
        }else {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante com id %d não encontrado.", id));
        }
    }
}
