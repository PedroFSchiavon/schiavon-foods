package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.api.model.dto.output.ProdutoDTO;
import br.com.schiavon.food.domain.models.Produto;
import br.com.schiavon.food.domain.services.RestauranteProdutoService;
import br.com.schiavon.food.domain.services.RestauranteService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes/{idRestaurante}/produtos")
public class RestauranteProdutoController {
    private final RestauranteProdutoService produtoService;

    private final ModelMapper modelMapper;

    public RestauranteProdutoController(RestauranteProdutoService produtoService, ModelMapper modelMapper) {
        this.produtoService = produtoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<ProdutoDTO> listar(@PathVariable Long idRestaurante){
        List<Produto> produtos = produtoService.listarProduto(idRestaurante);
        return toCollectionDTO(produtos);
    }

    @GetMapping("/{idProduto}")
    public ProdutoDTO buscar(@PathVariable Long idRestaurante, @PathVariable Long idProduto){
        return toDTO(produtoService.buscarProduto(idRestaurante, idProduto));
    }

    private ProdutoDTO toDTO(Produto produto){
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    private List<ProdutoDTO> toCollectionDTO(List<Produto> produtos){
        return produtos.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
