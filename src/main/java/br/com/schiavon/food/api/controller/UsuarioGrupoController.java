package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.api.model.dto.output.GrupoDTO;
import br.com.schiavon.food.domain.models.Grupo;
import br.com.schiavon.food.domain.models.Usuario;
import br.com.schiavon.food.domain.services.UsuarioGrupoService;
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
@RequestMapping("/usuarios/{idUsuario}/grupos")
public class UsuarioGrupoController {
    private final UsuarioGrupoService usuarioGrupoService;

    private final ModelMapper modelMapper;

    public UsuarioGrupoController(UsuarioGrupoService usuarioGrupoService, ModelMapper modelMapper) {
        this.usuarioGrupoService = usuarioGrupoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<GrupoDTO> listar(@PathVariable Long idUsuario){
        Usuario usuario = usuarioGrupoService.buscarUsuarioId(idUsuario);

        return toCollectionDTO(usuario.getGrupos());
    }

    @PutMapping("/{idGrupo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarGrupo(@PathVariable Long idUsuario, @PathVariable Long idGrupo){
        usuarioGrupoService.associarGrupo(idUsuario, idGrupo);
    }

    @DeleteMapping("/{idGrupo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarGrupo(@PathVariable Long idUsuario, @PathVariable Long idGrupo){
        usuarioGrupoService.desassociarGrupo(idUsuario, idGrupo);
    }


    private GrupoDTO toDTO(Grupo grupo){
        return modelMapper.map(grupo, GrupoDTO.class);
    }

    private List<GrupoDTO> toCollectionDTO(Set<Grupo> grupos){
        return grupos.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
