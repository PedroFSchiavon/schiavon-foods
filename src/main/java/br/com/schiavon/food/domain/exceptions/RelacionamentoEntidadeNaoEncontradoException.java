package br.com.schiavon.food.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RelacionamentoEntidadeNaoEncontradoException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public  RelacionamentoEntidadeNaoEncontradoException(String menssagem){
        super(menssagem);
    }
}
