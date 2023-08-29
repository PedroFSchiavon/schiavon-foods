package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.GrupoNaoEncontradaException;
import br.com.schiavon.food.domain.models.Grupo;
import br.com.schiavon.food.domain.repositories.GrupoRepository;
import org.springframework.stereotype.Service;

@Service
public class GrupoService {
    private final GrupoRepository grupoRepository;

    public GrupoService(GrupoRepository grupoRepository){
        this.grupoRepository = grupoRepository;
    }

    public Grupo buscarGrupoId(Long id){
        return grupoRepository.findById(id).orElseThrow(() -> new GrupoNaoEncontradaException(id));
    }
}
