package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.domain.services.UsuarioGrupoService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios/{idUsuario}/grupos")
public class UsuarioGrupoController {
    private final UsuarioGrupoService usuarioGrupoService;

    private final ModelMapper modelMapper;

    public UsuarioGrupoController(UsuarioGrupoService usuarioGrupoService, ModelMapper modelMapper) {
        this.usuarioGrupoService = usuarioGrupoService;
        this.modelMapper = modelMapper;
    }
}
