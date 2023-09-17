package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.api.model.dto.output.FormaPagamentoDTO;
import br.com.schiavon.food.domain.models.FormaPagamento;
import br.com.schiavon.food.domain.models.Restaurante;
import br.com.schiavon.food.domain.services.RestauranteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes/{idRestaurante}/forma-pagamento")
public class RestauranteFormaPagamentoController {
    private final RestauranteService restauranteService;

    private final ModelMapper modelMapper;

    public RestauranteFormaPagamentoController(RestauranteService restauranteService, ModelMapper modelMapper){
        this.restauranteService = restauranteService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<FormaPagamentoDTO> listar(@PathVariable Long idRestaurante){
        Restaurante restaurante = restauranteService.buscarRestauranteId(idRestaurante);

        return toCollectionDTO(restaurante.getFormaPagamento());
    }

    @DeleteMapping("/{idFormaPagamento}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarFormaPagamento(@PathVariable Long idRestaurante, @PathVariable Long idFormaPagamento){
        restauranteService.desassociarFormaPagamento(idRestaurante, idFormaPagamento);
    }

    @PutMapping("/{idFormaPagamento}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarFormaPagamento(@PathVariable Long idRestaurante, @PathVariable Long idFormaPagamento){
        restauranteService.associarFormaPagamento(idRestaurante, idFormaPagamento);
    }

    private FormaPagamentoDTO toDTO(FormaPagamento formaPagamento){
        return modelMapper.map(formaPagamento, FormaPagamentoDTO.class);
    }

    private List<FormaPagamentoDTO> toCollectionDTO(Set<FormaPagamento> formaPagamentos){
        return formaPagamentos.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
