package br.com.schiavon.food.core.validation;

import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

public class ValorZeroIncluiDescricaoValidation implements ConstraintValidator<ValorZeroIncluiDescricao, Object> {
    private String valorField;
    private String valorDescricao;
    private String descricao;

    @Override
    public void initialize(ValorZeroIncluiDescricao constraintAnnotation) {
        valorField = constraintAnnotation.valorField();
        valorDescricao = constraintAnnotation.descricaoField();
        descricao = constraintAnnotation.descricao();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        boolean valido = true;
        if(o != null){
            BigDecimal valor = null;
            String descricaoParaValidar = null;
            try {
                valor = (BigDecimal) BeanUtils.getPropertyDescriptor(o.getClass(), valorField)
                        .getReadMethod().invoke(o);

                descricaoParaValidar = (String) BeanUtils.getPropertyDescriptor(o.getClass(), valorDescricao)
                        .getReadMethod().invoke(o);
            } catch (Exception e) {
                throw new ValidationException(e);
            }
            
            if (valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null){
                valido = descricaoParaValidar.toLowerCase().contains(descricao.toLowerCase());
            }
        }

        return valido;
    }
}
