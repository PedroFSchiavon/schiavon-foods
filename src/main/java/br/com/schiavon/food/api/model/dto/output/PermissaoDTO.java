package br.com.schiavon.food.api.model.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoDTO {
    private long id;
    private String nome;
    private String descricao;
}
