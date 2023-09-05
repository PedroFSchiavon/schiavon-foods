package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.api.model.dto.input.usuario.UsuarioSenha;
import br.com.schiavon.food.domain.exceptions.SenhaDeUsuarioNaoCoincidemException;
import br.com.schiavon.food.domain.exceptions.UsuarioNaoEncontradaException;
import br.com.schiavon.food.domain.models.Usuario;
import br.com.schiavon.food.domain.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario cadastro(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario usuario){
        Usuario usuarioOld = buscarUsuarioId(id);

        usuario.setId(id);
        usuario.setSenha(usuarioOld.getSenha());
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void
    atualizaSenha(Long id, UsuarioSenha usuarioSenha){
        Usuario usuario = buscarUsuarioId(id);

        boolean eIgual = usuario.getSenha().equals(usuarioSenha.getSenhaAntiga());

        if (eIgual){
            usuario.setSenha(usuarioSenha.getSenhaNova());
        }else {
            throw new SenhaDeUsuarioNaoCoincidemException();
        }
    }


    public Usuario buscarUsuarioId(Long id){
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradaException(id));
    }
}
