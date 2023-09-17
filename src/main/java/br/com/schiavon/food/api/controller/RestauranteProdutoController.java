package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.domain.services.RestauranteProdutoService;
import br.com.schiavon.food.domain.services.RestauranteService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurantes/{idRestaurante/produtos}")
public class RestauranteProdutoController {
    private final RestauranteProdutoService produtoService;

    private final ModelMapper modelMapper;

    public RestauranteProdutoController(RestauranteProdutoService produtoService, ModelMapper modelMapper) {
        this.produtoService = produtoService;
        this.modelMapper = modelMapper;
    }


}
