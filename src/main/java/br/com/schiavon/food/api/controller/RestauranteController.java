package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.api.model.dto.input.RestauranteInputDTO;
import br.com.schiavon.food.api.model.dto.output.RestauranteDTO;
import br.com.schiavon.food.core.validation.ValidationPatchException;
import br.com.schiavon.food.domain.exceptions.naoencontrada.CidadeNaoEncontradaException;
import br.com.schiavon.food.domain.exceptions.naoencontrada.CozinhaNaoEncontradaException;
import br.com.schiavon.food.domain.exceptions.RelacionamentoEntidadeNaoEncontradoException;
import br.com.schiavon.food.domain.models.Restaurante;
import br.com.schiavon.food.domain.repositories.RestauranteRepository;
import br.com.schiavon.food.domain.services.RestauranteService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    private final RestauranteRepository restauranteRepository;

    private final RestauranteService restauranteService;

    private final SmartValidator validator;

    private final ModelMapper modelMapper;

    public RestauranteController(RestauranteRepository restauranteRepository, RestauranteService restauranteService,
                                 SmartValidator validator, ModelMapper modelMapper){
        this.restauranteRepository = restauranteRepository;
        this.restauranteService = restauranteService;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<RestauranteDTO> listar(){
        return toCollectionDTO(restauranteRepository.findAll());
    }

    @GetMapping("/nome")
    public List<RestauranteDTO> buscarPorNome(@RequestParam String nome){
        return toCollectionDTO(restauranteRepository.buscarPorNome(nome));
    }

    @GetMapping("/nome-taxa")
    public List<RestauranteDTO> buscarPorNomeETaxa(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal){
        return toCollectionDTO(restauranteRepository.buscaPorNomeETaxa(nome, taxaInicial, taxaFinal));
    }

    @GetMapping("/taxa-zero")
    public List<RestauranteDTO> restauranteTaxaZeroENome(String nome){
        return toCollectionDTO(restauranteRepository.buscarFreteGratis(nome));
    }

    @GetMapping("/primeiro")
    public RestauranteDTO buscarPrimeiro(){
        return toDTO(restauranteRepository.bucarPrimeiro().get());
    }

    @GetMapping("/{idRestaurante}")
    public RestauranteDTO buscar(@PathVariable Long idRestaurante){
        Restaurante restaurante = restauranteService.buscarRestauranteId(idRestaurante);
        return toDTO(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteDTO cadastro(@RequestBody @Valid RestauranteInputDTO restauranteInputDTO){
        try{
            Restaurante restaurante = toDomainModel(restauranteInputDTO);
            return toDTO(restauranteService.cadastro(restaurante));
        }catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e){
            throw new RelacionamentoEntidadeNaoEncontradoException(e.getMessage(), e);
        }
    }

    @PutMapping("/{idRestaurante}")
    public RestauranteDTO atualizar(@PathVariable Long idRestaurante, @RequestBody @Valid RestauranteInputDTO restauranteInputDTO){
        try{
            Restaurante restaurante = toDomainModel(restauranteInputDTO);
            return toDTO(restauranteService.atualizar(idRestaurante, restaurante));
        }catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e){
            throw new RelacionamentoEntidadeNaoEncontradoException(e.getMessage(), e);
        }
    }

    @PatchMapping("/{idRestaurante}")
    public RestauranteDTO atualizarParcial(@PathVariable Long idRestaurante, @RequestBody Map<String, Object> restauranteMap,
                                        HttpServletRequest request){
        Restaurante restaurante = restauranteService.buscarRestauranteId(idRestaurante);

        try {
            restauranteService.atualizarParcial(restaurante, restauranteMap, request);
        }catch (CozinhaNaoEncontradaException e){
            throw new RelacionamentoEntidadeNaoEncontradoException(e.getMessage(), e);
        }

        validate(restaurante, "restaurante");

        return toDTO(restauranteService.atualizar(idRestaurante, restaurante));
    }

    private void validate(Restaurante restaurante, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
        validator.validate(restaurante, bindingResult);

        if(bindingResult.hasErrors()){
            throw new ValidationPatchException(bindingResult);
        }
    }

    @DeleteMapping("/{idRestaurante}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long idRestaurante){
        restauranteService.deletar(idRestaurante);
    }

    @PutMapping("/{idRestaurante}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long idRestaurante){
        restauranteService.ativar(idRestaurante);
    }

    @DeleteMapping("/{idRestaurante}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long idRestaurante){
        restauranteService.inativar(idRestaurante);
    }

    @PutMapping("/{idRestaurante}/abrir")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrir(@PathVariable Long idRestaurante){
        restauranteService.abrirRestaurante(idRestaurante);
    }

    @PutMapping("/{idRestaurante}/fechar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fechar(@PathVariable Long idRestaurante){
        restauranteService.fecharRestaurante(idRestaurante);
    }

    private RestauranteDTO toDTO(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteDTO.class);
    }

    private List<RestauranteDTO> toCollectionDTO(List<Restaurante> restaurantes){
        return restaurantes.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private Restaurante toDomainModel(RestauranteInputDTO restauranteInputDTO){
        return modelMapper.map(restauranteInputDTO, Restaurante.class);
    }
}
