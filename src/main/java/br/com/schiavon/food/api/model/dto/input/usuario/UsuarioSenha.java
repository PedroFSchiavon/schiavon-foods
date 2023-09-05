package br.com.schiavon.food.api.model.dto.input.usuario;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UsuarioSenha {
    @NotBlank
    String senhaAntiga;
    @NotBlank
    String senhaNova;
}
