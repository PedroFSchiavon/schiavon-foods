package br.com.schiavon.food.domain.exceptions;

public class NegocioException extends RuntimeException{
    private static final long serialVersionUID = 12L;

    public NegocioException(String message) {
        super(message);
    }
}
