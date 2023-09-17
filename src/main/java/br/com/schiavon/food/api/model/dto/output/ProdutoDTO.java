package br.com.schiavon.food.api.model.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoDTO {
    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private boolean ativo;
}
