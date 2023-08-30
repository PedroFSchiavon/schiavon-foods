package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.EntidadeEmUsoException;
import br.com.schiavon.food.domain.exceptions.GrupoNaoEncontradaException;
import br.com.schiavon.food.domain.models.Grupo;
import br.com.schiavon.food.domain.repositories.GrupoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GrupoService {
    private final GrupoRepository grupoRepository;

    public static final String GRUPO_ID_EM_USO = "Não foi possível deletar a grupo com o id %d," +
            " pois esta em uso no momento.";

    public GrupoService(GrupoRepository grupoRepository){
        this.grupoRepository = grupoRepository;
    }

    @Transactional
    public Grupo cadastrar(Grupo grupo){
        return grupoRepository.save(grupo);
    }

    @Transactional
    public Grupo atualizar(Long id, Grupo grupo){
        if(grupoRepository.existsById(id)){
            grupo.setId(id);
            return grupoRepository.save(grupo);
        }
        throw new GrupoNaoEncontradaException(id);
    }

    @Transactional
    public void deletar(Long id){
        Grupo grupo = buscarGrupoId(id);
        try{
            grupoRepository.delete(grupo);
            grupoRepository.flush();
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format(GRUPO_ID_EM_USO, id));
        }
    }

    public Grupo buscarGrupoId(Long id){
        return grupoRepository.findById(id).orElseThrow(() -> new GrupoNaoEncontradaException(id));
    }
}
