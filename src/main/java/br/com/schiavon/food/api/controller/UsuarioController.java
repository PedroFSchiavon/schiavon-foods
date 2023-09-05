package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.api.model.dto.input.usuario.UsuarioInput;
import br.com.schiavon.food.api.model.dto.input.usuario.UsuarioInputAtDTO;
import br.com.schiavon.food.api.model.dto.input.usuario.UsuarioInputCadDTO;
import br.com.schiavon.food.api.model.dto.input.usuario.UsuarioSenha;
import br.com.schiavon.food.api.model.dto.output.UsuarioDTO;
import br.com.schiavon.food.domain.models.Usuario;
import br.com.schiavon.food.domain.repositories.UsuarioRepository;
import br.com.schiavon.food.domain.services.UsuarioService;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @GetMapping
    public List<UsuarioDTO> listar(){
        return toCollectionDTO(usuarioRepository.findAll());
    }

    @GetMapping("/{idUsuario}")
    public UsuarioDTO buscar(@PathVariable Long idUsuario){
        return toDTO(usuarioService.buscarUsuarioId(idUsuario));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO cadastro(@RequestBody @Valid UsuarioInputCadDTO usuarioInputCadDTO){
        Usuario usuario = toDomainModel(usuarioInputCadDTO);
        return toDTO(usuarioService.cadastro(usuario));
    }

    @PutMapping("/{idUsuario}")
    public UsuarioDTO atualizar(@PathVariable Long idUsuario, @RequestBody @Valid UsuarioInputAtDTO usuarioInputAtDTO){
        Usuario usuario = toDomainModel(usuarioInputAtDTO);
        return toDTO(usuarioService.atualizar(idUsuario, usuario));
    }

    @PutMapping("/{idUsuario}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void trocarSenha(@PathVariable Long idUsuario, @RequestBody @Valid UsuarioSenha usuarioSenha){
        usuarioService.atualizaSenha(idUsuario,usuarioSenha);
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
