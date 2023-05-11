package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.domain.models.Estado;
import br.com.schiavon.food.domain.repositories.EstadoRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {
    private final EstadoRepository estadoRepository;

    public EstadoController(EstadoRepository estadoRepository){
        this.estadoRepository = estadoRepository;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Estado> listar(){
        return estadoRepository.findAll();
    }
}
