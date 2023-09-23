package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.models.Grupo;
import br.com.schiavon.food.domain.models.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioGrupoService {
    private final UsuarioService usuarioService;

    private final GrupoService grupoService;

    public UsuarioGrupoService(UsuarioService usuarioService, GrupoService grupoService) {
        this.usuarioService = usuarioService;
        this.grupoService = grupoService;
    }

    @Transactional
    public void associarGrupo(Long idUsuario, Long idGrupo){
        Usuario usuario = buscarUsuarioId(idUsuario);
        Grupo grupo = grupoService.buscarGrupoId(idGrupo);

        usuario.getGrupos().add(grupo);
    }

    @Transactional
    public void desassociarGrupo(Long idUsuario, Long idGrupo){
        Usuario usuario = buscarUsuarioId(idUsuario);
        Grupo grupo = grupoService.buscarGrupoId(idGrupo);

        usuario.getGrupos().remove(grupo);
    }



    public Usuario buscarUsuarioId(Long id){
        return usuarioService.buscarUsuarioId(id);
    }
}
