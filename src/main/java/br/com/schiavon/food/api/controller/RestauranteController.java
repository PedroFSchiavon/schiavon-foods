package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.domain.models.Restaurante;
import br.com.schiavon.food.domain.repositories.RestauranteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    private final RestauranteRepository restauranteRepository;

    public RestauranteController(RestauranteRepository restauranteRepository){
        this.restauranteRepository = restauranteRepository;
    }

    @GetMapping
    public List<Restaurante> listar(){
        return restauranteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id){
        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(id);
        return restauranteOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
