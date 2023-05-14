package br.com.schiavon.food.domain.exceptions;

public class CozinhaNaoEncontradaException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public CozinhaNaoEncontradaException(String menssagem){
        super(menssagem);
    }
}
