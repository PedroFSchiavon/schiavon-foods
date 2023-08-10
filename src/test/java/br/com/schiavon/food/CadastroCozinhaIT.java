package br.com.schiavon.food;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {
    @LocalServerPort
    private int port;

    @Autowired
    private Flyway flyway;

    @BeforeEach
    public void configurations() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/cozinhas";

        flyway.migrate();
    }

    @Test
    public void deveRetornar200_QuandoConsultarCozinha() {
        given().accept(ContentType.JSON)
                .when().get()
                .then().statusCode(200);
    }

    @Test
    public void deveConter4Cozinhas_QuandoConsultarCozinha() {
        given().accept(ContentType.JSON)
                .when().get()
                .then().body("", Matchers.hasSize(3)).body("nome", Matchers.hasItems("Mineira", "Amazonense"));
    }

    @Test
    public void deveRetornar201_QuandoCadastrarUmaCozinha(){
        given().body("{\"nome\": \"Inglesa\"}").contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().post()
                .then().statusCode(HttpStatus.CREATED.value());
    }
}
