package br.com.schiavon.food.domain.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException{
    private static final long serialVersionUID = -8026186595439651605L;

    public RecursoNaoEncontradoException(String message) {
        super(message);
    }
}
