package br.com.schiavon.food.testes;

import br.com.schiavon.food.FoodApplication;
import br.com.schiavon.food.domain.models.Cozinha;
import br.com.schiavon.food.domain.repositories.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class GeradorCozinhaRepository {
        public static CozinhaRepository getCozinhaRepository(){
        ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApplication.class)
                .web(WebApplicationType.NONE).run();
        return applicationContext.getBean(CozinhaRepository.class);
    }
}
