package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.EntidadeEmUsoException;
import br.com.schiavon.food.domain.exceptions.naoencontrada.RestauranteNaoEncontradaException;
import br.com.schiavon.food.domain.models.Cidade;
import br.com.schiavon.food.domain.models.Cozinha;
import br.com.schiavon.food.domain.models.FormaPagamento;
import br.com.schiavon.food.domain.models.Restaurante;
import br.com.schiavon.food.domain.repositories.RestauranteRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private final CidadeService cidadeService;

    private final FormaPagamentoService formaPagamentoService;

    public RestauranteService(RestauranteRepository restauranteRepository, CozinhaService cozinhaService,
                              CidadeService cidadeService, FormaPagamentoService formaPagamentoService) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaService = cozinhaService;
        this.cidadeService = cidadeService;
        this.formaPagamentoService =formaPagamentoService;
    }

    @Transactional
    public Restaurante cadastro(Restaurante restaurante) {
        long idCozinha = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaService.buscarCozinhaId(idCozinha);

        long cidadeId = restaurante.getEndereco().getCidade().getId();
        Cidade cidade = cidadeService.buscarCidadeId(cidadeId);

        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);
        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public Restaurante atualizar(Long id, Restaurante restaurante) {
        Restaurante restauranteAntigo = buscarRestauranteId(id);
        long cozinhaId = restaurante.getCozinha().getId();
        cozinhaService.buscarCozinhaId(cozinhaId);

        long cidadeId = restaurante.getEndereco().getCidade().getId();
        cidadeService.buscarCidadeId(cidadeId);

        restaurante.setId(id);
        restaurante.setDataCadastro(restauranteAntigo.getDataCadastro());
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

    @Transactional
    public void deletar(Long id) {
        Restaurante restaurante = buscarRestauranteId(id);
        try {
            restauranteRepository.delete(restaurante);
            restauranteRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(RESTAURANTE_ID_EM_USO, id));
        }
    }

    @Transactional
    public void ativar(Long id){
        Restaurante restaurante = buscarRestauranteId(id);
        restaurante.ativar();
    }

    @Transactional
    public void inativar(Long id){
        Restaurante restaurante = buscarRestauranteId(id);
        restaurante.inativar();
    }

    @Transactional
    public void desassociarFormaPagamento(Long idRestaurante, Long idFormaPagamento){
        Restaurante restaurante = buscarRestauranteId(idRestaurante);
        FormaPagamento formaPagamento = formaPagamentoService.buscarFormaPagamentoID(idFormaPagamento);

        restaurante.getFormaPagamento().remove(formaPagamento);
    }

    @Transactional
    public void associarFormaPagamento(Long idRestaurante, Long idFormaPagamento){
        Restaurante restaurante = buscarRestauranteId(idRestaurante);
        FormaPagamento formaPagamento = formaPagamentoService.buscarFormaPagamentoID(idFormaPagamento);

        restaurante.getFormaPagamento().add(formaPagamento);
    }

    @Transactional
    public void abrirRestaurante(Long id){
        Restaurante restaurante = buscarRestauranteId(id);
        restaurante.abrirRestaurante();
    }

    @Transactional
    public void fecharRestaurante(Long id){
        Restaurante restaurante = buscarRestauranteId(id);
        restaurante.fecharRestaurante();
    }

    public Restaurante buscarRestauranteId(Long id) {
        return restauranteRepository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradaException(id));
    }
}
