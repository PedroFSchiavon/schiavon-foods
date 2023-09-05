package br.com.schiavon.food.domain.exceptions.naoencontrada;

import br.com.schiavon.food.domain.exceptions.naoencontrada.EntidadeNaoEncontradaException;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;
    public static final String COZINHA_NAO_ENCONTRADA = "Cozinha de id %d não foi encontrada.";

    public CozinhaNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public CozinhaNaoEncontradaException(Long id){
        this(String.format(COZINHA_NAO_ENCONTRADA, id));
    }

}
