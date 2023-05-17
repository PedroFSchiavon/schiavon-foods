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
    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;

    public CidadeService(CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
    }

    public Cidade cadastro(Cidade cidade) {
        long idEstado = cidade.getEstado().getId();
        if (estadoRepository.existsById(idEstado))
            return cidadeRepository.save(cidade);
        throw new RelacionamentoEntidadeNaoEncontradoException(String
                .format("Estado com id %d não encontrado.", idEstado));
    }

    public Cidade atualizar(Long id, Cidade cidade) {
        if (!cidadeRepository.existsById(id))
            throw new EntidadeNaoEncontradaException(String.format("Cidade de id %d não encontrada", id));
        else if (!estadoRepository.existsById(cidade.getEstado().getId())) {
            throw new RelacionamentoEntidadeNaoEncontradoException(String
                    .format("Estado de id %d não encontrado", cidade.getEstado().getId()));
        } else {
            cidade.setId(id);
            return cidadeRepository.save(cidade);
        }
    }

    public void deletar(Long id) {
        Optional<Cidade> cidadeOptional = cidadeRepository.findById(id);

        if (cidadeOptional.isPresent()) {
            cidadeRepository.delete(cidadeOptional.get());
        } else {
            throw new EntidadeNaoEncontradaException(String.format("Cidade de id %d não encontrada", id));
        }
    }
}
