package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.api.model.dto.output.PermissaoDTO;
import br.com.schiavon.food.domain.models.Grupo;
import br.com.schiavon.food.domain.models.Permissao;
import br.com.schiavon.food.domain.services.GrupoPermissaoService;
import br.com.schiavon.food.domain.services.GrupoService;
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
@RequestMapping("/grupos/{idGrupo}/permissoes")
public class GrupoPermissaoController {
    private final GrupoService grupoService;

    private final ModelMapper modelMapper;

    private final GrupoPermissaoService grupoPermissaoService;

    public GrupoPermissaoController(GrupoService grupoService, ModelMapper modelMapper, GrupoPermissaoService grupoPermissaoService) {
        this.grupoService = grupoService;
        this.modelMapper = modelMapper;
        this.grupoPermissaoService = grupoPermissaoService;
    }

    @GetMapping
    public List<PermissaoDTO> listar(@PathVariable Long idGrupo){
        Grupo grupo = grupoService.buscarGrupoId(idGrupo);

        return toCollectionDTO(grupo.getPermissoes());
    }

    @PutMapping("/{idPermissao}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarPermissao(@PathVariable Long idGrupo, @PathVariable Long idPermissao){
        grupoPermissaoService.associarPermissao(idGrupo, idPermissao);
    }

    @DeleteMapping("/{idPermissao}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarPermissao(@PathVariable Long idGrupo, @PathVariable Long idPermissao){
        grupoPermissaoService.desassociarPermissao(idGrupo, idPermissao);
    }

    private PermissaoDTO toDTO(Permissao permissao){
        return modelMapper.map(permissao, PermissaoDTO.class);
    }

    private List<PermissaoDTO> toCollectionDTO(Set<Permissao> permissoes){
        return permissoes.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
