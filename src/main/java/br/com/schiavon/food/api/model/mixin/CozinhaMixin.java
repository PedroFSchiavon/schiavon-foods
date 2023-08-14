package br.com.schiavon.food.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class CozinhaMixin {
    @JsonProperty("nome")
    private String nome;
}
