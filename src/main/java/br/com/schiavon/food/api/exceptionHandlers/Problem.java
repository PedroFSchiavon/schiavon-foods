package br.com.schiavon.food.api.exceptionHandlers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class Problem {
    private int status;
    private String type;
    private String title;
    private String detail;

    private String userMessage;
    private LocalDateTime timestamp;

    private List<ProblemFieldValidation> fields;

    public Problem(int status, String type, String title, String detail, String userMessage) {
        this.status = status;
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.userMessage = userMessage;
        this.timestamp = LocalDateTime.now();
    }

    public Problem(int status, String title){
        this.status = status;
        this.title = title;
        this.timestamp = LocalDateTime.now();
    }

    public void setFields(List<ProblemFieldValidation> fields) {
        this.fields = fields;
    }
}
