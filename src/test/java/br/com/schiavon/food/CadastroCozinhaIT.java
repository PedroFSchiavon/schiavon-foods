package br.com.schiavon.food;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIT {
    @LocalServerPort
    private int port;

    @Test
    public void deveRetornar200_QuandoConsultarCozinha(){

    }
}
