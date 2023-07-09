package br.com.schiavon.food.api.exceptionHandlers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProblemType {
    ENTIDADE_NAO_ENCONTRADA("https://schiavonfood.com.br/entidade_nao_encontrada", "Entidade não encontrada");

    String type;
    String title;

}
