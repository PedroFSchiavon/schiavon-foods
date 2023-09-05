package br.com.schiavon.food.domain.exceptions.naoencontrada;

public class GrupoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;
    public static final String GRUPO_NAO_ENCONTRADA = "Grupo de id %d não foi encontrada.";

    public GrupoNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public GrupoNaoEncontradaException(Long id){
        this(String.format(GRUPO_NAO_ENCONTRADA, id));
    }

}
