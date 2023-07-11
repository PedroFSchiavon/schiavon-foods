package br.com.schiavon.food.api.exceptionHandlers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum ProblemType {
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada."),
    RELACIONAMENTO_ENTIDADE_NAO_ENCONTRADO("/relacionamento-entidade-nao-encontrado",
            "Relacionamento entidade não encontrado."),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso."),
    ERRO_DE_SINTAXE("/erro-de-sintaxe", "Erro de sintaxe.");

    String type;
    String title;

    ProblemType(String path, String title) {
        this.type = "https://schiavonfood.com.br" + path;
        this.title = title;
    }
}
