package br.com.schiavon.food.testes;

import br.com.schiavon.food.FoodApplication;
import br.com.schiavon.food.domain.repositories.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class GeradorRestauranteRepository {
        public static RestauranteRepository getCozinhaRepository(){
        ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApplication.class)
                .web(WebApplicationType.NONE).run();
        return applicationContext.getBean(RestauranteRepository.class);
    }
}
