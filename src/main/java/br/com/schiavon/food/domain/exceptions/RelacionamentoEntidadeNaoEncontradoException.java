package br.com.schiavon.food.domain.exceptions;

public class RelacionamentoEntidadeNaoEncontradoException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public  RelacionamentoEntidadeNaoEncontradoException(String menssagem, Throwable throwable){
        super(menssagem, throwable);
    }
}
