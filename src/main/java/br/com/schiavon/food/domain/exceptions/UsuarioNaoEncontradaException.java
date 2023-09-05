package br.com.schiavon.food.domain.exceptions;

public class UsuarioNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;
    public static final String USUARIO_NAO_ENCONTRADA = "Usuario de id %d não foi encontrada.";

    public UsuarioNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public UsuarioNaoEncontradaException(Long id){
        this(String.format(USUARIO_NAO_ENCONTRADA, id));
    }

}
