package br.com.schiavon.food.domain.exceptions.naoencontrada;

public class PedidoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;
    public static final String PEDIDO_NAO_ENCONTRADA = "Pedido de id %d não foi encontrada.";

    public PedidoNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public PedidoNaoEncontradaException(Long id){
        this(String.format(PEDIDO_NAO_ENCONTRADA, id));
    }

}
