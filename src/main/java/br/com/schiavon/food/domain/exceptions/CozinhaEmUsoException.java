package br.com.schiavon.food.domain.exceptions;

public class CozinhaEmUsoException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public CozinhaEmUsoException(String menssagem){
        super(menssagem);
    }
}
