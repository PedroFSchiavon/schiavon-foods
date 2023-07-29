package br.com.schiavon.food.core.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class MultiploValidation implements ConstraintValidator<Multiplo, Number> {
    int numeroMultiplo;
    @Override
    public void initialize(Multiplo constraintAnnotation) {
        numeroMultiplo = constraintAnnotation.number();
    }

    @Override
    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        boolean validado = true;
        if (number != null){
            BigDecimal valorAtributo = BigDecimal.valueOf(number.doubleValue());
            BigDecimal resto = valorAtributo.remainder(BigDecimal.valueOf(numeroMultiplo));
            validado = BigDecimal.ZERO.compareTo(resto) == 0;
        }

        return validado;
    }
}
