package br.com.schiavon.food.domain.exceptions;

public class SenhaDeUsuarioNaoCoincidemException extends RuntimeException{
    private static final long serialVersionUID = -1534507346333844277L;

    public SenhaDeUsuarioNaoCoincidemException(){
        super("Senha informada não coincidem com a atual.");
    }
}
