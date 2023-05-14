package br.com.schiavon.food.domain.services;

import br.com.schiavon.food.domain.exceptions.CozinhaEmUsoException;
import br.com.schiavon.food.domain.exceptions.CozinhaNaoEncontradaException;
import br.com.schiavon.food.domain.models.Cozinha;
import br.com.schiavon.food.domain.repositories.CozinhaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CozinhaService {
    private final CozinhaRepository cozinhaRepository;

    public CozinhaService(CozinhaRepository cozinhaRepository){
        this.cozinhaRepository = cozinhaRepository;
    }

    public Cozinha cadastro(Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }

    public void deletar(Long id){
        Optional<Cozinha> cozinhaOptional = cozinhaRepository.findById(id);
        if(cozinhaOptional.isPresent()){
            try{
                cozinhaRepository.delete(cozinhaOptional.get());
            }catch (DataIntegrityViolationException e){
                throw new CozinhaEmUsoException(String.format("Cozinha de id %d se encontra em uso no momento.", id));
            }
        }else {
            throw new CozinhaNaoEncontradaException(String.format("Cozinha de id %d não encontrada.", id));
        }
    }
}
