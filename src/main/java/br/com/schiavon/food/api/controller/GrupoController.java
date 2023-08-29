package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.domain.repositories.GrupoRepository;
import br.com.schiavon.food.domain.services.GrupoService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
    private final GrupoService grupoService;

    private final GrupoRepository grupoRepository;

    private final ModelMapper modelMapper;

    public GrupoController(GrupoService grupoService, GrupoRepository grupoRepository, ModelMapper modelMapper) {
        this.grupoService = grupoService;
        this.grupoRepository = grupoRepository;
        this.modelMapper = modelMapper;
    }
}
