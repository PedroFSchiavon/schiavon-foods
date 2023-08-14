package br.com.schiavon.food.api.model.mixin;

import br.com.schiavon.food.domain.models.Cozinha;
import br.com.schiavon.food.domain.models.Endereco;
import br.com.schiavon.food.domain.models.FormaPagamento;
import br.com.schiavon.food.domain.models.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

public abstract class RestauranteMixin {
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "nome"}, allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private List<FormaPagamento> formaPagamento;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos;

    @JsonIgnore
    LocalDateTime dataCadastro;

    @JsonIgnore
    LocalDateTime dataAtualizacao;
}
