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
    public static final String RESTAURANTE_ID_NÃO_ENCONTRADO = "Restaurante com id %d não encontrado.";
    private final RestauranteRepository restauranteRepository;
        private final CozinhaService cozinhaService;

    public RestauranteService(RestauranteRepository restauranteRepository, CozinhaService cozinhaService) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaService = cozinhaService;
    }

    public Restaurante cadastro(Restaurante restaurante) {
        long idCozinha = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaService.buscarCozinhaId(idCozinha);

        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    public Restaurante atualizar(Long id, Restaurante restaurante) {
        Restaurante restauranteDb = buscarRestauranteId(id);
        long cozinhaId = restaurante.getCozinha().getId();
        cozinhaService.buscarCozinhaId(cozinhaId);

        restaurante.setId(id);
        return restauranteRepository.save(restaurante);
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

    public void deletar(Long id) {
        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(id);

        if (restauranteOptional.isPresent()) {
            try {
                restauranteRepository.delete(restauranteOptional.get());
            } catch (DataIntegrityViolationException e) {
                throw new EntidadeEmUsoException(String.format("Restaurante de id %d esta em uso por outra entidade", id));
            }
        } else {
            throw new EntidadeNaoEncontradaException(String.format(RESTAURANTE_ID_NÃO_ENCONTRADO, id));
        }
    }

    public Restaurante buscarRestauranteId(Long id) {
        return restauranteRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String
                .format(RESTAURANTE_ID_NÃO_ENCONTRADO, id)));
    }
}
