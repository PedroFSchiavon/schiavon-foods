package br.com.schiavon.food.api.controller;

import br.com.schiavon.food.domain.models.Cozinha;
import br.com.schiavon.food.domain.repositories.CozinhaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    private final CozinhaRepository cozinhaRepository;

    public CozinhaController(CozinhaRepository cozinhaRepository){
        this.cozinhaRepository = cozinhaRepository;
    }

    @GetMapping
    public List<Cozinha> listar(){
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cozinha buscar(@PathVariable Long id){
        return cozinhaRepository.findById(id).get();
    }
}
