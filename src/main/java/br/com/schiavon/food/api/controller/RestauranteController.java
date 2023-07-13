package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.domain.exceptions.CozinhaNaoEncontradaException;
import br.com.schiavon.food.domain.exceptions.RelacionamentoEntidadeNaoEncontradoException;
import br.com.schiavon.food.domain.models.Restaurante;
import br.com.schiavon.food.domain.repositories.RestauranteRepository;
import br.com.schiavon.food.domain.services.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    private final RestauranteRepository restauranteRepository;
    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteRepository restauranteRepository, RestauranteService restauranteService){
        this.restauranteRepository = restauranteRepository;
        this.restauranteService = restauranteService;
    }

    @GetMapping
    public List<Restaurante> listar(){
        return restauranteRepository.findAll();
    }

    @GetMapping("/nome")
    public List<Restaurante> buscarPorNome(@RequestParam String nome){
        return restauranteRepository.buscarPorNome(nome);
    }

    @GetMapping("/nome-taxa")
    public List<Restaurante> buscarPorNomeETaxa(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal){

        return restauranteRepository.buscaPorNomeETaxa(nome, taxaInicial, taxaFinal);
    }

    @GetMapping("/taxa-zero")
    public List<Restaurante> restauranteTaxaZeroENome(String nome){
        return restauranteRepository.buscarFreteGratis(nome);
    }

    @GetMapping("/primeiro")
    public Restaurante buscarPrimeiro(){
        return restauranteRepository.bucarPrimeiro().get();
    }

    @GetMapping("/{idRestaurante}")
    public Restaurante buscar(@PathVariable Long idRestaurante){
        return restauranteService.buscarRestauranteId(idRestaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante cadastro(@RequestBody @Valid Restaurante restaurante){
        try{
            return restauranteService.cadastro(restaurante);
        }catch (CozinhaNaoEncontradaException e){
            throw new RelacionamentoEntidadeNaoEncontradoException(e.getMessage(), e);
        }
    }

    @PutMapping("/{idRestaurante}")
    public Restaurante atualizar(@PathVariable Long idRestaurante, @RequestBody Restaurante restaurante){
        try{
            return restauranteService.atualizar(idRestaurante, restaurante);
        }catch (CozinhaNaoEncontradaException e){
            throw new RelacionamentoEntidadeNaoEncontradoException(e.getMessage(), e);
        }
    }

    @PatchMapping("/{idRestaurante}")
    public Restaurante atualizarParcial(@PathVariable Long idRestaurante, @RequestBody Map<String, Object> restauranteMap,
                                        HttpServletRequest request){
        Restaurante restaurante = restauranteService.buscarRestauranteId(idRestaurante);

        try {
            restauranteService.atualizarParcial(restaurante, restauranteMap, request);
        }catch (CozinhaNaoEncontradaException e){
            throw new RelacionamentoEntidadeNaoEncontradoException(e.getMessage(), e);
        }

        return atualizar(idRestaurante, restaurante);
    }

    @DeleteMapping("/{idRestaurante}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long idRestaurante){
        restauranteService.deletar(idRestaurante);
    }
}
