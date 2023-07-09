package br.com.schiavon.food.domain.exceptions;

public class EstadoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;
    public static final String ESTADO_NAO_ENCONTRADA = "Estado de id %d não foi encontrada.";

    public EstadoNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public EstadoNaoEncontradaException(Long id){
        this(String.format(ESTADO_NAO_ENCONTRADA, id));
    }

}
