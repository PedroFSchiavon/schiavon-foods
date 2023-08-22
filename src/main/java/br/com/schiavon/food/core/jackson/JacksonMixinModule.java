package br.com.schiavon.food.core.jackson;

import br.com.schiavon.food.api.model.mixin.CidadeMixin;
import br.com.schiavon.food.api.model.mixin.CozinhaMixin;
import br.com.schiavon.food.api.model.mixin.ProdutoMixin;
import br.com.schiavon.food.domain.models.Cidade;
import br.com.schiavon.food.domain.models.Cozinha;
import br.com.schiavon.food.domain.models.Produto;
import br.com.schiavon.food.domain.models.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {
    private final static long serialVersionUID = 1L;

    public JacksonMixinModule() {
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
        setMixInAnnotation(Produto.class, ProdutoMixin.class);
    }
}
