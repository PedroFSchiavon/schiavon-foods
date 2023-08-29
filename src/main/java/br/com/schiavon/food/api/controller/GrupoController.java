package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.api.model.dto.input.GrupoInputDTO;
import br.com.schiavon.food.api.model.dto.output.GrupoDTO;
import br.com.schiavon.food.domain.models.Grupo;
import br.com.schiavon.food.domain.repositories.GrupoRepository;
import br.com.schiavon.food.domain.services.GrupoService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
    private final GrupoService grupoService;

    private final GrupoRepository grupoRepository;

    private final ModelMapper modelMapper;

    public GrupoController(GrupoService grupoService, GrupoRepository grupoRepository, ModelMapper modelMapper) {
        this.grupoService = grupoService;
        this.grupoRepository = grupoRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<GrupoDTO> listar(){
        return toCollectionDTO(grupoRepository.findAll());
    }

    @GetMapping("/{idGrupo}")
    public GrupoDTO buscar(@PathVariable Long idGrupo){
        return toDTO(grupoService.buscarGrupoId(idGrupo));
    }

    private GrupoDTO toDTO(Grupo grupo){
        return modelMapper.map(grupo, GrupoDTO.class);
    }

    private List<GrupoDTO> toCollectionDTO(List<Grupo> grupos){
        return grupos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private Grupo toDomainModel(GrupoInputDTO grupoInputDTO){
        return modelMapper.map(grupoInputDTO, Grupo.class);
    }
}
