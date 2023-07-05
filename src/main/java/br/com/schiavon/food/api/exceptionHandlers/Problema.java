package br.com.schiavon.food.api.exceptionHandlers;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Problema {
    private String mensagem;
    private LocalDateTime dateTime;
}
