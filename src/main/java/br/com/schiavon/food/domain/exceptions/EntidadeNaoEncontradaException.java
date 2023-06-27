package br.com.schiavon.food.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntidadeNaoEncontradaException extends ResponseStatusException {
    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(HttpStatus status, String menssagem) {
        super(status, menssagem);
    }

    public EntidadeNaoEncontradaException(String menssagem) {
        this(HttpStatus.NOT_FOUND, menssagem);
    }
}
