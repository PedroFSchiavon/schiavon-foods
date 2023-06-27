package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.schiavon.food.domain.exceptions.RelacionamentoEntidadeNaoEncontradoException;
import br.com.schiavon.food.domain.models.Restaurante;
import br.com.schiavon.food.domain.repositories.RestauranteRepository;
import br.com.schiavon.food.domain.services.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id){
        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(id);
        return restauranteOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> cadastro(@RequestBody Restaurante restaurante){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteService.cadastro(restaurante));
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante){
        try {
            return ResponseEntity.ok(restauranteService.atualizar(id, restaurante));
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }catch (RelacionamentoEntidadeNaoEncontradoException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> restauranteMap){
        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(id);
        if (restauranteOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Restaurante restaurante = restauranteOptional.get();
        restauranteService.atualizarParcial(restaurante, restauranteMap);

        return atualizar(id, restaurante);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        restauranteService.deletar(id);
    }
}
