package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.naoencontrada.ProdutoNaoEncontradoException;
import br.com.schiavon.food.domain.models.Produto;
import br.com.schiavon.food.domain.models.Restaurante;
import br.com.schiavon.food.domain.repositories.ProdutoRepository;
import br.com.schiavon.food.domain.repositories.RestauranteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteProdutoService {
    private final RestauranteService restauranteService;


    private final ProdutoRepository produtoRepository;

    public RestauranteProdutoService(RestauranteService restauranteService,
                                     ProdutoRepository produtoRepository){
        this.restauranteService = restauranteService;
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProduto(Long idRestaurante){
        Restaurante restaurante = restauranteService.buscarRestauranteId(idRestaurante);

        return restaurante.getProdutos();
    }

    @Transactional
    public Produto cadastro(Long idRestaurante, Produto produto){
        Restaurante restaurante = restauranteService.buscarRestauranteId(idRestaurante);
        produto.setRestaurante(restaurante);

        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto atualizar(Long idRestaurante, Long idProduto, Produto produto) {
        Produto produtoAtual = buscarProdutoID(idRestaurante, idProduto);

        produto.setRestaurante(produtoAtual.getRestaurante());
        produto.setId(produtoAtual.getId());

        return produtoRepository.save(produto);
    }

    public Produto buscarProdutoID(Long idRestaurante, Long idProduto){
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
