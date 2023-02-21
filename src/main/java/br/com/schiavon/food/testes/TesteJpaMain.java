package br.com.schiavon.food.testes;

import br.com.schiavon.food.domain.models.Cozinha;
import br.com.schiavon.food.domain.repositories.CozinhaRepository;

import java.util.List;

public class TesteJpaMain {
    public static void main(String[] args) {
        CozinhaRepository cozinhaRepository = GeradorCozinhaRepository.getCozinhaRepository();
        Cozinha cozinha = new Cozinha();
        Cozinha cozinha1 = new Cozinha();

        cozinha.setNome("Japonesa");
        cozinha1.setNome("Árabe");

        cozinhaRepository.save(cozinha);
        cozinhaRepository.save(cozinha1);

        List<Cozinha> cozinhas = cozinhaRepository.findAll();

        cozinhas.forEach(cozinha2 -> System.out.println(cozinha2.getId() + "=====" + cozinha2.getNome()));
    }
}
