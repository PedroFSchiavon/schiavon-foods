package br.com.schiavon.food.domain.exceptions;

public class UsuarioNegocioException extends RuntimeException{
    private static final long serialVersionUID = -1534507346333844277L;

    public UsuarioNegocioException(String message){
        super(message);
    }
}
