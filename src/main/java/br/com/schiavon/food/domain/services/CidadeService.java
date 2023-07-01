package br.com.schiavon.food.domain.services;

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
    public static final String CIDADE_ID_NÃO_ENCONTRADA = "Cidade de id %d não encontrada";
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
        try {
            estadoService.buscaEstadoId(cidade.getEstado().getId());
        }catch (EntidadeNaoEncontradaException e){
            throw new RelacionamentoEntidadeNaoEncontradoException(e.getMessage());
        }

        cidade.setId(id);
        return cidadeRepository.save(cidade);
    }

    public void deletar(Long id) {
        try{
            Cidade cidade = buscarCidadeId(id);
            cidadeRepository.delete(cidade);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("Cidade com o id %d esta em uso por oura entidade.", id));
        }

    }

    public Cidade buscarCidadeId(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String
                .format(CIDADE_ID_NÃO_ENCONTRADA, id)));
    }
}
