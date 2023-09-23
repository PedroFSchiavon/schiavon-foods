package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.models.Restaurante;
import br.com.schiavon.food.domain.models.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResponsavelService {
    private final RestauranteService restauranteService;

    private final UsuarioService usuarioService;

    public ResponsavelService(RestauranteService restauranteService, UsuarioService usuarioService) {
        this.restauranteService = restauranteService;
        this.usuarioService = usuarioService;
    }

    @Transactional
    public void associarResponsavel(Long idRestaurente, Long idUsuario){
        Restaurante restaurante = buscarRestauranteId(idRestaurente);
        Usuario usuario = usuarioService.buscarUsuarioId(idUsuario);

        restaurante.getResponsaveis().add(usuario);
    }

    @Transactional
    public void desassociarResponsavel(Long idRestaurente, Long idUsuario){
        Restaurante restaurante = buscarRestauranteId(idRestaurente);
        Usuario usuario = usuarioService.buscarUsuarioId(idUsuario);

        restaurante.getResponsaveis().remove(usuario);
    }

    public Restaurante buscarRestauranteId(Long id){
        return restauranteService.buscarRestauranteId(id);
    }
}
