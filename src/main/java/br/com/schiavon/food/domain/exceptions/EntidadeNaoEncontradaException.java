package br.com.schiavon.food.domain.exceptions;

public class EntidadeNaoEncontradaException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public EntidadeNaoEncontradaException(String menssagem){
        super(menssagem);
    }
}
