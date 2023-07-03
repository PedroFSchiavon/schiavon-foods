package br.com.schiavon.food.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RestauranteNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;
    public static final String RESTAURANTE_NAO_ENCONTRADA = "Restaurante de id %d não encontrada.";

    public RestauranteNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public RestauranteNaoEncontradaException(Long id){
        this(String.format(RESTAURANTE_NAO_ENCONTRADA, id));
    }

}
