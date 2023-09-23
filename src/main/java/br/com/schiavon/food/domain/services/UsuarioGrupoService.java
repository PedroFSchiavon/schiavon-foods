package br.com.schiavon.food.domain.services;

import org.springframework.stereotype.Service;

@Service
public class UsuarioGrupoService {
    private final UsuarioService usuarioService;

    private final GrupoService grupoService;

    public UsuarioGrupoService(UsuarioService usuarioService, GrupoService grupoService) {
        this.usuarioService = usuarioService;
        this.grupoService = grupoService;
    }
}
