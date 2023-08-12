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
public class CadastroRestauranteIT {
    private final int ID_RESTAURANTE_INVALIDO = 200;

    @LocalServerPort
    private  int port;

    @Autowired
    private Flyway flyway;

    @BeforeEach
    public void configurations(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurantes";

        flyway.migrate();
    }

    @Test
    public void deveRetornar200E4Restaurantes_QuandoListarTodosRestaurantes(){
        given().accept(ContentType.JSON)
                .when().get()
                .then().statusCode(HttpStatus.OK.value()).body("", Matchers.hasSize(4));
    }

    @Test
    public void deveRetornar200EViniBurguer_QuandoConsultarRestaurante(){
        given().accept(ContentType.JSON).pathParam("idRestaurante", 2)
                .when().get("/{idRestaurante}")
                .then().statusCode(HttpStatus.OK.value()).body("nome", Matchers.equalTo("Vini burguer"));
    }

    @Test
    public void deveRetornar404_QuandoInformarIdRestauranteErrado(){
        given().accept(ContentType.JSON).pathParam("idRestaurante", ID_RESTAURANTE_INVALIDO)
                .when().get("/{idRestaurante}")
                .then().statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornar201EIdDoRestaurante1_QuandoCadastrarRestauranteComSucesso(){
        String json = "{\n" +
                "    \"nome\": \"Tito Forneria\",\n" +
                "    \"taxaFrete\": 6.32,\n" +
                "    \"cozinha\": {\n" +
                "        \"id\": 3\n" +
                "    }\n" +
                "}";

        given().accept(ContentType.JSON).contentType(ContentType.JSON).body(json)
                .when().post()
                .then().statusCode(HttpStatus.CREATED.value()).body("id", Matchers.equalTo(5));
    }

    @Test
    public void deveRetornar400_QuandoCadastrarRestauranteComCozinhaInexistente(){
        String json = "{\n" +
                "    \"nome\": \"Tito Forneria\",\n" +
                "    \"taxaFrete\": 6.32,\n" +
                "    \"cozinha\": {\n" +
                "        \"id\": 20\n" +
                "    }\n" +
                "}";

        given().accept(ContentType.JSON).contentType(ContentType.JSON).body(json)
                .when().post()
                .then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void deveRetornar400_QuandoCadastrarRestauranteComTaxaFreteNegativo(){
        String json = "{\n" +
                "    \"nome\": \"Tito Forneria\",\n" +
                "    \"taxaFrete\": -6.32,\n" +
                "    \"cozinha\": {\n" +
                "        \"id\": 2\n" +
                "    }\n" +
                "}";

        given().accept(ContentType.JSON).contentType(ContentType.JSON).body(json)
                .when().post()
                .then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void deveRetornar400_QuandoCadastrarRestauranteComCozinhaNula(){
        String json = "{\n" +
                "    \"nome\": \"Tito Forneria\",\n" +
                "    \"taxaFrete\": 6.32,\n" +
                "}";

        given().accept(ContentType.JSON).contentType(ContentType.JSON).body(json)
                .when().post()
                .then().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void deveRetornar400_QuandoCadastrarRestauranteComTaxaFrete0ENomeNaoConterFreteGratis(){
        String json = "{\n" +
                "    \"nome\": \"Tito Forneria\",\n" +
                "    \"taxaFrete\": 0,\n" +
                "    \"cozinha\": {\n" +
                "        \"id\": 20\n" +
                "    }\n" +
                "}";

        given().accept(ContentType.JSON).contentType(ContentType.JSON).body(json)
                .when().post()
                .then().statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
