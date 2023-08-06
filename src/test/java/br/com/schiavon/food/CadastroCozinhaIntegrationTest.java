package br.com.schiavon.food;

import br.com.schiavon.food.domain.exceptions.CozinhaNaoEncontradaException;
import br.com.schiavon.food.domain.exceptions.EntidadeEmUsoException;
import br.com.schiavon.food.domain.models.Cozinha;
import br.com.schiavon.food.domain.services.CozinhaService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaIntegrationTest {
    @Autowired
    private CozinhaService cozinhaService;

    @Test
    public void deveAtribuirId_QuandoCadastrarCozinhaCorretamente(){
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Russa");

        cozinha = cozinhaService.cadastro(cozinha);

        Assertions.assertThat(cozinha).isNotNull();
        Assertions.assertThat(cozinha.getId()).isNotNull();
    }

    @Test(expected = ConstraintViolationException.class)
    public void deveFalhar_QuandoCadastrarCozinhaSemNome(){
        Cozinha cozinha = new Cozinha();
        cozinha.setNome(null);

        cozinha = cozinhaService.cadastro(cozinha);
    }

    @Test(expected = EntidadeEmUsoException.class)
    public void deveFalhar_QuandoCozinhaEstiverEmUso(){
        cozinhaService.deletar(1L);
    }

    @Test(expected = CozinhaNaoEncontradaException.class)
    public void deveFalhar_QuandoExcluirCozinhaInexistente(){
        cozinhaService.deletar(22L);
    }
}
