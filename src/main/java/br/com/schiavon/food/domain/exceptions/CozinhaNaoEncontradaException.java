package br.com.schiavon.food.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;
    public static final String COZINHA_NAO_ENCONTRADA = "Cozinha de id %d não encontrada.";

    public CozinhaNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public CozinhaNaoEncontradaException(Long id){
        this(String.format(COZINHA_NAO_ENCONTRADA, id));
    }

}
