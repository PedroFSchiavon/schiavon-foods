package br.com.schiavon.food.api.exceptionHandlers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProblemFieldValidation {
    String name;
    String userMessage;
}
