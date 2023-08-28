package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.api.model.dto.input.FormaPagamentoInputDTO;
import br.com.schiavon.food.api.model.dto.output.FormaPagamentoDTO;
import br.com.schiavon.food.domain.models.FormaPagamento;
import br.com.schiavon.food.domain.repositories.FormaPagamentoRepository;
import br.com.schiavon.food.domain.services.FormaPagamentoService;
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
@RequestMapping("/forma-pagamento")
public class FormaPagamentoController {
    private final FormaPagamentoRepository formaPagamentoRepository;

    private final FormaPagamentoService formaPagamentoService;

    private final ModelMapper modelMapper;

    public FormaPagamentoController(FormaPagamentoRepository formaPagamentoRepository,
                                    FormaPagamentoService formaPagamentoService, ModelMapper modelMapper) {
        this.formaPagamentoRepository = formaPagamentoRepository;
        this.formaPagamentoService = formaPagamentoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<FormaPagamentoDTO> listar(){
        return toCollectionDTO(formaPagamentoRepository.findAll());
    }

    @GetMapping("/{idFormaPagamento}")
    public FormaPagamentoDTO bucar(@PathVariable Long idFormaPagamento){
        return toDTO(formaPagamentoService.buscarFormaPagamentoID(idFormaPagamento));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoDTO cadastrar(@RequestBody FormaPagamentoInputDTO formaPagamentoInputDTO){
        return toDTO(formaPagamentoService.cadastrar(toDomainModel(formaPagamentoInputDTO)));
    }

    private FormaPagamentoDTO toDTO(FormaPagamento formaPagamento){
        return modelMapper.map(formaPagamento, FormaPagamentoDTO.class);
    }

    private List<FormaPagamentoDTO> toCollectionDTO(List<FormaPagamento> formasPagamentos){
        return formasPagamentos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private FormaPagamento toDomainModel(FormaPagamentoInputDTO formaPagamentoInputDTO){
        return modelMapper.map(formaPagamentoInputDTO, FormaPagamento.class);
    }
}
