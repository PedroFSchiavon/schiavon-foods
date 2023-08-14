package br.com.schiavon.food.api.model.mixin;

import br.com.schiavon.food.domain.models.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class ProdutoMixin {
    @JsonIgnore
    private Restaurante restaurante;
}
