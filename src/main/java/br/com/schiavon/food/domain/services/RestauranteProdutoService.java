package br.com.schiavon.food.domain.services;

import org.springframework.stereotype.Service;

@Service
public class RestauranteProdutoService {
    private final RestauranteService restauranteService;

    public RestauranteProdutoService(RestauranteService restauranteService){
        this.restauranteService = restauranteService;
    }
}
