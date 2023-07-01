package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.schiavon.food.domain.exceptions.RelacionamentoEntidadeNaoEncontradoException;
import br.com.schiavon.food.domain.models.Cidade;
import br.com.schiavon.food.domain.repositories.CidadeRepository;
import br.com.schiavon.food.domain.repositories.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CidadeService {
    public static final String CIDADE_ID_NÃO_ENCONTRADA = "Cidade de id %d não encontrada";
    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;
    private final EstadoService estadoService;

    public CidadeService(CidadeRepository cidadeRepository, EstadoRepository estadoRepository, EstadoService estadoService) {
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
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
        Cidade cidade = buscarCidadeId(id);
        cidadeRepository.delete(cidade);
    }

    public Cidade buscarCidadeId(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String
                .format(CIDADE_ID_NÃO_ENCONTRADA, id)));
    }
}
