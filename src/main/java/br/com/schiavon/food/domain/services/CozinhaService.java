package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.EntidadeEmUsoException;
import br.com.schiavon.food.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.schiavon.food.domain.models.Cozinha;
import br.com.schiavon.food.domain.repositories.CozinhaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CozinhaService {
    public static final String COZINHA_NAO_ENCONTRADA = "Cozinha de id %d não encontrada.";
    private final CozinhaRepository cozinhaRepository;

    public CozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    public Cozinha cadastro(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public void deletar(Long id) {
        Cozinha cozinha = buscarCozinhaId(id);
        try {
            cozinhaRepository.delete(cozinha);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Cozinha de id %d se encontra em uso no momento.", id));
        }
    }

    public Cozinha atualizar(Long id, Cozinha cozinha) {
        if (cozinhaRepository.existsById(id)) {
            cozinha.setId(id);
            return cozinhaRepository.save(cozinha);
        }
        throw new EntidadeNaoEncontradaException(String.format(COZINHA_NAO_ENCONTRADA, id));
    }

    public Cozinha buscarCozinhaId(Long id) {
        return cozinhaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String
                .format(COZINHA_NAO_ENCONTRADA, id)));
    }
}
