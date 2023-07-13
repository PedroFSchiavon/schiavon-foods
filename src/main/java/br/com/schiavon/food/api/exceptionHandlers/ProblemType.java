package br.com.schiavon.food.api.exceptionHandlers;

import lombok.Getter;

@Getter
public enum ProblemType {
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado."),
    RELACIONAMENTO_ENTIDADE_NAO_ENCONTRADO("/relacionamento-entidade-nao-encontrado",
            "Relacionamento entidade não encontrado."),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso."),
    ERRO_DE_SINTAXE("/erro-de-sintaxe", "Erro de sintaxe."),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parametro invalido.");

    String type;
    String title;

    ProblemType(String path, String title) {
        this.type = "https://schiavonfood.com.br" + path;
        this.title = title;
    }
}
