package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.models.Cozinha;
import br.com.schiavon.food.domain.repositories.CozinhaRepository;
import org.springframework.stereotype.Service;

@Service
public class CozinhaService {
    private final CozinhaRepository cozinhaRepository;

    public CozinhaService(CozinhaRepository cozinhaRepository){
        this.cozinhaRepository = cozinhaRepository;
    }

    public Cozinha cadastro(Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }
}
