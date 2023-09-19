package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.api.model.dto.input.ProdutoInputDTO;
import br.com.schiavon.food.api.model.dto.output.ProdutoDTO;
import br.com.schiavon.food.domain.models.Produto;
import br.com.schiavon.food.domain.models.Restaurante;
import br.com.schiavon.food.domain.services.RestauranteProdutoService;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
        return toDTO(produtoService.buscarProdutoID(idRestaurante, idProduto));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO cadastro(@PathVariable Long idRestaurante, @RequestBody @Valid ProdutoInputDTO produtoInputDTO){
        return toDTO(produtoService.cadastro(idRestaurante, toDomainModel(produtoInputDTO)));
    }

    private ProdutoDTO toDTO(Produto produto){
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    private List<ProdutoDTO> toCollectionDTO(List<Produto> produtos){
        return produtos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private Produto toDomainModel(ProdutoInputDTO produtoInputDTO){
        return modelMapper.map(produtoInputDTO, Produto.class);
    }
}
