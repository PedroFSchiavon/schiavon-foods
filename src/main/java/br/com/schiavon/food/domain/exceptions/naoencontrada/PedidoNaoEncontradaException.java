package br.com.schiavon.food.domain.exceptions.naoencontrada;

import java.util.UUID;

public class PedidoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;
    public static final String PEDIDO_NAO_ENCONTRADA = "Pedido de id %s não foi encontrada.";

    public PedidoNaoEncontradaException(String codigo){
        super(String.format(PEDIDO_NAO_ENCONTRADA, codigo));
    }

}
