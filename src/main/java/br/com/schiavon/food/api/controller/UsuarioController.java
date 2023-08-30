package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.domain.repositories.UsuarioRepository;
import br.com.schiavon.food.domain.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
