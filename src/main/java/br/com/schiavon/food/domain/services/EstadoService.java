package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.EntidadeEmUsoException;
import br.com.schiavon.food.domain.exceptions.EstadoNaoEncontradaException;
import br.com.schiavon.food.domain.models.Estado;
import br.com.schiavon.food.domain.repositories.EstadoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {
    public static final String ESTADO_ID_EM_USO_POR = "Não foi possível deletar o estado com o id %d, pois esta em uso no momento.";
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
        throw new EstadoNaoEncontradaException(id);
    }

    public void deletar(Long id){
        Estado estado = buscaEstadoId(id);
        try{
            estadoRepository.delete(estado);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format(ESTADO_ID_EM_USO_POR, id));
        }
    }

    public Estado buscaEstadoId(Long id) {
        return estadoRepository.findById(id).orElseThrow(() -> new EstadoNaoEncontradaException(id));
    }
}
