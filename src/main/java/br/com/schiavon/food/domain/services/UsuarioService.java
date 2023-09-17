package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.api.model.dto.input.usuario.UsuarioSenha;
import br.com.schiavon.food.domain.exceptions.UsuarioNegocioException;
import br.com.schiavon.food.domain.exceptions.naoencontrada.UsuarioNaoEncontradaException;
import br.com.schiavon.food.domain.models.Usuario;
import br.com.schiavon.food.domain.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario cadastro(Usuario usuario){
        emailExiste(usuario.getEmail(), usuario);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Long id, Usuario usuario){
        Usuario usuarioOld = buscarUsuarioId(id);

        usuario.setId(id);
        usuario.setSenha(usuarioOld.getSenha());

        emailExiste(usuario.getEmail(), usuario);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void atualizaSenha(Long id, UsuarioSenha usuarioSenha){
        Usuario usuario = buscarUsuarioId(id);

        if (usuario.senhaCoincidem(usuarioSenha.getSenhaAntiga())){
            usuario.setSenha(usuarioSenha.getSenhaNova());
        }else {
            throw new UsuarioNegocioException("Senha informada não coincidem com a atual.");
        }
    }


    public void emailExiste(String email, Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        if (usuarioOptional.isPresent() && !usuarioOptional.get().equals(usuario)){
            throw new UsuarioNegocioException(
                    String.format("E-mail %s já cadastrado em outro usuário.", email));
        }
    }

    public Usuario buscarUsuarioId(Long id){
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradaException(id));
    }
}
