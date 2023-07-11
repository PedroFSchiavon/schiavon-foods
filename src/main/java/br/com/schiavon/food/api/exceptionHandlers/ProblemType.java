package br.com.schiavon.food.api.exceptionHandlers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum ProblemType {
    ENTIDADE_NAO_ENCONTRADA("/entidade_nao_encontrada", "Entidade não encontrada."),
    RELACIONAMENTO_ENTIDADE_NAO_ENCONTRADO("/relacionamento_entidade_nao_encontrado",
            "Relacionamento entidade não encontrado."),
    ENTIDADE_EM_USO("/entidade_em_uso", "Entidade em uso.");

    String type;
    String title;

    ProblemType(String path, String title) {
        this.type = "https://schiavonfood.com.br" + path;
        this.title = title;
    }
}
