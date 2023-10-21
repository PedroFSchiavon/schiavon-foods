package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.naoencontrada.ProdutoNaoEncontradoException;
import br.com.schiavon.food.domain.models.Produto;
import br.com.schiavon.food.domain.models.Restaurante;
import br.com.schiavon.food.domain.repositories.ProdutoRepository;
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

        return produtoRepository.findByRestaurante(restaurante);
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
        Optional<Produto> produtoOptional = produtoRepository.findByRestaurante_IdAndId(idRestaurante, idProduto);

        if(produtoOptional.isEmpty()){
            throw new ProdutoNaoEncontradoException(
                    String.format("O recurso de id %d não encontrado no restaurante de id %d.",
                            idProduto, idRestaurante));
        }

        return produtoOptional.get();
    }
}
