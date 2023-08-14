package br.com.schiavon.food.core.jackson;

import br.com.schiavon.food.api.model.mixin.RestauranteMixin;
import br.com.schiavon.food.domain.models.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {
    public JacksonMixinModule(){
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
    }
}
