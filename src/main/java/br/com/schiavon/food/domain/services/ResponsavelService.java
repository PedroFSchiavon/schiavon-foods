package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.models.Restaurante;
import org.springframework.stereotype.Service;

@Service
public class ResponsavelService {
    private final RestauranteService restauranteService;

    private final UsuarioService usuarioService;

    public ResponsavelService(RestauranteService restauranteService, UsuarioService usuarioService) {
        this.restauranteService = restauranteService;
        this.usuarioService = usuarioService;
    }

    public Restaurante buscarRestauranteId(Long id){
        return restauranteService.buscarRestauranteId(id);
    }
}
