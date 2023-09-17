package br.com.schiavon.food.domain.exceptions.naoencontrada;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException{
    private static final long serialVersionUID = -8026186595439651605L;

    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }
}
