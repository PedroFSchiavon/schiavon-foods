package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.EntidadeEmUsoException;
import br.com.schiavon.food.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.schiavon.food.domain.models.Estado;
import br.com.schiavon.food.domain.repositories.EstadoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstadoService {
    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository){
        this.estadoRepository = estadoRepository;
    }

    public Estado cadastro(Estado estado){
        return estadoRepository.save(estado);
    }

    public Estado atualizar(Long id, Estado estado){
        if(estadoRepository.existsById(id)){
            estado.setId(id);
            return estadoRepository.save(estado);
        }
        throw new EntidadeNaoEncontradaException(String.format("Estado com id %d não encontrado", id));
    }

    public void deletar(Long id){
        Optional<Estado> estadoOptional = estadoRepository.findById(id);
        try {
            if (estadoOptional.isPresent()) {
                estadoRepository.delete(estadoOptional.get());
            } else
                throw new EntidadeNaoEncontradaException(String.format("Estado com id %d não encontrado", id));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("Estado com o id %d esta em uso por oura entidade.", id));
        }
    }
}
