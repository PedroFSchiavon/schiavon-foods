package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.naoencontrada.ProdutoNaoEncontradoException;
import br.com.schiavon.food.domain.models.Produto;
import br.com.schiavon.food.domain.models.Restaurante;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteProdutoService {
    private final RestauranteService restauranteService;

    public RestauranteProdutoService(RestauranteService restauranteService){
        this.restauranteService = restauranteService;
    }

    public List<Produto> listarProduto(Long idRestaurante){
        Restaurante restaurante = restauranteService.buscarRestauranteId(idRestaurante);

        return restaurante.getProdutos();
    }

    public Produto buscarProduto(Long idRestaurante, Long idProduto){
        Restaurante restaurante = restauranteService.buscarRestauranteId(idRestaurante);

        Optional<Produto> produtoOptional = restaurante.getProdutos().stream()
                .filter(produto -> idProduto.equals(produto.getId()))
                .findAny();

        if(produtoOptional.isEmpty()){
            throw new ProdutoNaoEncontradoException(
                    String.format("O recurso de id %d não encontrado no restaurante %s.",
                            idProduto, restaurante.getNome()));
        }

        return produtoOptional.get();
    }
}
