package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.api.model.dto.input.EstadoInputDTO;
import br.com.schiavon.food.api.model.dto.output.EstadoDTO;
import br.com.schiavon.food.domain.models.Estado;
import br.com.schiavon.food.domain.repositories.EstadoRepository;
import br.com.schiavon.food.domain.services.EstadoService;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estados")
public class EstadoController {
    private final EstadoRepository estadoRepository;
    private final EstadoService estadoService;
    private final ModelMapper modelMapper;

    public EstadoController(EstadoRepository estadoRepository, EstadoService estadoService, ModelMapper modelMapper) {
        this.estadoRepository = estadoRepository;
        this.estadoService = estadoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<EstadoDTO> listar() {
        return toCollectionDTO(estadoRepository.findAll());
    }

    @GetMapping("/{idEstado}")
    public EstadoDTO buscar(@PathVariable Long idEstado) {
        return toDTO(estadoService.buscaEstadoId(idEstado));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoDTO cadastro(@RequestBody @Valid EstadoInputDTO estadoInputDTO) {
        Estado estado = toModelDomain(estadoInputDTO);
        return toDTO(estadoService.cadastro(estado));
    }

    @PutMapping("/{idEstado}")
    public EstadoDTO atualizar(@PathVariable Long idEstado, @RequestBody @Valid EstadoInputDTO estadoInputDTO) {
        Estado estado = toModelDomain(estadoInputDTO);
        return toDTO(estadoService.atualizar(idEstado, estado));
    }

    @DeleteMapping("/{idEstado}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long idEstado) {
        estadoService.deletar(idEstado);
    }

    private EstadoDTO toDTO(Estado estado){
        return modelMapper.map(estado, EstadoDTO.class);
    }

    private List<EstadoDTO> toCollectionDTO(List<Estado> estados){
        return estados.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private Estado toModelDomain(EstadoInputDTO estadoInputDTO){
        return modelMapper.map(estadoInputDTO, Estado.class);
    }
}
