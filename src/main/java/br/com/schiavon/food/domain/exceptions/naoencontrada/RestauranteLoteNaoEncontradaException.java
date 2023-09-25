package br.com.schiavon.food.domain.exceptions.naoencontrada;

public class RestauranteLoteNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;
    public static final String RESTAURANTE_NAO_ENCONTRADA = "Restaurante de id %d não foi encontrada.";

    public RestauranteLoteNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public RestauranteLoteNaoEncontradaException(Long id){
        this(String.format(RESTAURANTE_NAO_ENCONTRADA, id));
    }

}
