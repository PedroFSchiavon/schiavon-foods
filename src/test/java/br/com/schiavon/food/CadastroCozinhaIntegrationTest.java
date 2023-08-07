package br.com.schiavon.food;

import br.com.schiavon.food.domain.models.Cozinha;
import br.com.schiavon.food.domain.services.CozinhaService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


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

    @Test
    public void deveFalhar_QuandoCadastrarCozinhaSemNome(){
        Cozinha cozinha = new Cozinha();
        cozinha.setNome(null);
        Exception exception = assertThrows(Exception.class,
                () -> cozinhaService.cadastro(cozinha));

        String menssagemObtida = exception.getMessage();
        String menssagemCorreta = "Validation failed for classes";

        assertTrue(menssagemObtida.contains(menssagemCorreta));
    }

    @Test
    public void deveFalhar_QuandoCozinhaEstiverEmUso(){
        Exception exception = assertThrows(Exception.class,
                () ->  cozinhaService.deletar(1L));
        String menssagemObtida = exception.getMessage();
        String menssagemCorreta = "Não foi possível deletar";

        assertTrue(menssagemObtida.contains(menssagemCorreta));
    }

    @Test
    public void deveFalhar_QuandoExcluirCozinhaInexistente(){
        Exception exception = assertThrows(Exception.class,
                () ->  cozinhaService.deletar(22L));
        String menssagemObtida = exception.getMessage();
        String menssagemCorreta = "não foi encontrada";

        assertTrue(menssagemObtida.contains(menssagemCorreta));
    }
}
