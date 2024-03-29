package br.com.schiavon.food.domain.exceptions.naoencontrada;

public class RestauranteNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;
    public static final String RESTAURANTE_NAO_ENCONTRADA = "Restaurante de id %d não foi encontrada.";

    public RestauranteNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public RestauranteNaoEncontradaException(Long id){
        this(String.format(RESTAURANTE_NAO_ENCONTRADA, id));
    }

}
