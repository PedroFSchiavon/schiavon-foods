package br.com.schiavon.food.domain.exceptions.naoencontrada;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;
    public static final String PERMISSAO_NAO_ENCONTRADA = "Permissão de id %d não foi encontrada.";

    public PermissaoNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public PermissaoNaoEncontradaException(Long id){
        this(String.format(PERMISSAO_NAO_ENCONTRADA, id));
    }

}
