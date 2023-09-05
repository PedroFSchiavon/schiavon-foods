package br.com.schiavon.food.domain.exceptions.naoencontrada;

public abstract class EntidadeNaoEncontradaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }

}
