package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.CozinhaNaoEncontradaException;
import br.com.schiavon.food.domain.exceptions.EntidadeEmUsoException;
import br.com.schiavon.food.domain.models.Cozinha;
import br.com.schiavon.food.domain.repositories.CozinhaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CozinhaService {
    public static final String COZINHA_ID_EM_USO = "Não foi possível deletar a cozinha com o id %d, pois esta em uso no momento..";
    private final CozinhaRepository cozinhaRepository;

    public CozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    @Transactional
    public Cozinha cadastro(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public void deletar(Long id) {
        Cozinha cozinha = buscarCozinhaId(id);
        try {
            cozinhaRepository.delete(cozinha);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(COZINHA_ID_EM_USO, id));
        }
    }

    @Transactional
    public Cozinha atualizar(Long id, Cozinha cozinha) {
        if (cozinhaRepository.existsById(id)) {
            cozinha.setId(id);
            return cozinhaRepository.save(cozinha);
        }
        throw new CozinhaNaoEncontradaException(id);
    }

    public Cozinha buscarCozinhaId(Long id) {
        return cozinhaRepository.findById(id).orElseThrow(() -> new CozinhaNaoEncontradaException(id));
    }
}
