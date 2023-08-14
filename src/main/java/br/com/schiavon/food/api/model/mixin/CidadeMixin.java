package br.com.schiavon.food.api.model.mixin;

import br.com.schiavon.food.domain.models.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public abstract class CidadeMixin {
    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;
}
