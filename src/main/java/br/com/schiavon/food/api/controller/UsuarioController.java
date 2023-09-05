package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.api.model.dto.input.usuario.UsuarioInput;
import br.com.schiavon.food.api.model.dto.output.UsuarioDTO;
import br.com.schiavon.food.domain.models.Usuario;
import br.com.schiavon.food.domain.repositories.UsuarioRepository;
import br.com.schiavon.food.domain.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    private final UsuarioRepository usuarioRepository;

    private final ModelMapper modelMapper;

    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    private UsuarioDTO toDTO(Usuario usuario){
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    private List<UsuarioDTO> toCollectionDTO(List<Usuario> usuarios){
        return usuarios.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private Usuario toDomainModel(UsuarioInput usuarioInput){
        return modelMapper.map(usuarioInput, Usuario.class);
    }
}
