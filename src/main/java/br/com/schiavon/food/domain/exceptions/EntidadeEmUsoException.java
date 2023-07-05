package br.com.schiavon.food.domain.exceptions;

public class EntidadeEmUsoException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public EntidadeEmUsoException(String menssagem){
        super(menssagem);
    }
}
