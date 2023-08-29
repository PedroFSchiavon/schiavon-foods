package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.repositories.GrupoRepository;
import org.springframework.stereotype.Service;

@Service
public class GrupoService {
    private final GrupoRepository grupoRepository;

    public GrupoService(GrupoRepository grupoRepository){
        this.grupoRepository = grupoRepository;
    }
}
