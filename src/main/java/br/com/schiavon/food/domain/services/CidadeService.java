package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.CidadeNaoEncontradaException;
import br.com.schiavon.food.domain.exceptions.EntidadeEmUsoException;
import br.com.schiavon.food.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.schiavon.food.domain.exceptions.RelacionamentoEntidadeNaoEncontradoException;
import br.com.schiavon.food.domain.models.Cidade;
import br.com.schiavon.food.domain.repositories.CidadeRepository;
import br.com.schiavon.food.domain.repositories.EstadoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CidadeService {
    public static final String CIDADE_ID_EM_USO = "Não foi possível deletar a cidade com o id %d, pois esta em uso no momento.";
    private final CidadeRepository cidadeRepository;
    private final EstadoService estadoService;

    public CidadeService(CidadeRepository cidadeRepository, EstadoService estadoService) {
        this.cidadeRepository = cidadeRepository;
        this.estadoService = estadoService;
    }

    public Cidade cadastro(Cidade cidade) {
        long idEstado = cidade.getEstado().getId();
        estadoService.buscaEstadoId(idEstado);

        return cidadeRepository.save(cidade);
    }

    public Cidade atualizar(Long id, Cidade cidade) {
        buscarCidadeId(id);
        estadoService.buscaEstadoId(cidade.getEstado().getId());

        cidade.setId(id);
        return cidadeRepository.save(cidade);
    }

    public void deletar(Long id) {
        try{
            Cidade cidade = buscarCidadeId(id);
            cidadeRepository.delete(cidade);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format(CIDADE_ID_EM_USO, id));
        }

    }

    public Cidade buscarCidadeId(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() -> new CidadeNaoEncontradaException(id));
    }
}
