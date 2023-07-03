package br.com.schiavon.food.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;
    public static final String CIDADE_NAO_ENCONTRADA = "Cidade de id %d não encontrada.";

    public CidadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public CidadeNaoEncontradaException(Long id){
        this(String.format(CIDADE_NAO_ENCONTRADA, id));
    }

}
