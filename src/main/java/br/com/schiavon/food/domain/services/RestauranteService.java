package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.EntidadeEmUsoException;
import br.com.schiavon.food.domain.exceptions.RestauranteNaoEncontradaException;
import br.com.schiavon.food.domain.models.Cozinha;
import br.com.schiavon.food.domain.models.Restaurante;
import br.com.schiavon.food.domain.repositories.RestauranteRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Map;

@Service
public class RestauranteService {
    public static final String RESTAURANTE_ID_EM_USO = "Não foi possível deletar o restaurante" +
            " com o id %d, pois esta em uso no momento.";
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
        buscarRestauranteId(id);
        long cozinhaId = restaurante.getCozinha().getId();
        cozinhaService.buscarCozinhaId(cozinhaId);

        restaurante.setId(id);
        return restauranteRepository.save(restaurante);
    }

    public void atualizarParcial(Restaurante restauranteDestino, Map<String, Object> dadosOrigem,
                                 HttpServletRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,true);

        ServletServerHttpRequest httpRequest = new ServletServerHttpRequest(request);

        try {
            Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
            System.out.println(restauranteOrigem);

            dadosOrigem.forEach((chave, valor) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, chave);
                field.setAccessible(true);

                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            });
        }catch (IllegalArgumentException e){
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, httpRequest);
        }
    }

    public void deletar(Long id) {
        Restaurante restaurante = buscarRestauranteId(id);
        try {
            restauranteRepository.delete(restaurante);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(RESTAURANTE_ID_EM_USO, id));
        }
    }

    public Restaurante buscarRestauranteId(Long id) {
        return restauranteRepository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradaException(id));
    }
}
