package br.com.schiavon.food.api.exceptionHandlers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@AllArgsConstructor
public class Problem {
    private int status;
    private String type;
    private String title;
    private String detail;

    public Problem(int status, String title){
        this.status = status;
        this.title = title;
    }
}
