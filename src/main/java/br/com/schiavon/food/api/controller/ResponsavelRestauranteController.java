package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.api.model.dto.output.UsuarioDTO;
import br.com.schiavon.food.domain.models.Restaurante;
import br.com.schiavon.food.domain.models.Usuario;
import br.com.schiavon.food.domain.services.ResponsavelService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes/{idRestaurante}/responsaveis")
public class ResponsavelRestauranteController {
    private final ResponsavelService responsavelService;

    private final ModelMapper modelMapper;

    public ResponsavelRestauranteController(ResponsavelService responsavelService, ModelMapper modelMapper) {
        this.responsavelService = responsavelService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<UsuarioDTO> listar(@PathVariable Long idRestaurante){
        Restaurante restaurante = responsavelService.buscarRestauranteId(idRestaurante);

        return toCollectionDTO(restaurante.getResponsaveis());
    }

    private UsuarioDTO toDTO(Usuario usuario){
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    private List<UsuarioDTO> toCollectionDTO(Set<Usuario> usuarios){
        return usuarios.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
