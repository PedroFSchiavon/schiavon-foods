package br.com.schiavon.food.api.model.dto.input;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInputDTO {
    @NotBlank
    private String nome;
}
