package br.com.schiavon.food.domain.exceptions;

public abstract class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;
    public static final String CIDADE_NAO_ENCONTRADA = "Cidade de id %d não encontrada.";

    public CidadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public CidadeNaoEncontradaException(Long id){
        this(String.format(CIDADE_NAO_ENCONTRADA, id));
    }

}
