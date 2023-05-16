package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.models.Estado;
import br.com.schiavon.food.domain.repositories.EstadoRepository;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {
    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository){
        this.estadoRepository = estadoRepository;
    }

    public Estado cadastro(Estado estado){
        return estadoRepository.save(estado);
    }
}
