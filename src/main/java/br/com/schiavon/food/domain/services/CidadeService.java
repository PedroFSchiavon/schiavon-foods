package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.RelacionamentoEntidadeNaoEncontradoException;
import br.com.schiavon.food.domain.models.Cidade;
import br.com.schiavon.food.domain.repositories.CidadeRepository;
import br.com.schiavon.food.domain.repositories.EstadoRepository;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {
    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;

    public CidadeService(CidadeRepository cidadeRepository, EstadoRepository estadoRepository){
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
    }

    public Cidade cadastro(Cidade cidade){
        long idEstado = cidade.getEstado().getId();
        if (estadoRepository.existsById(idEstado))
            return cidadeRepository.save(cidade);
        throw new RelacionamentoEntidadeNaoEncontradoException(String
                .format("Estado com id %d não encontrado.", idEstado));
    }
}
