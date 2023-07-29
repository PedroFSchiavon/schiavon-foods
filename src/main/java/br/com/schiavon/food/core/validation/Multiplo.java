package br.com.schiavon.food.core.validation;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.PositiveOrZero;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER,
        ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MultiploValidation.class})
public @interface Multiplo {
    @OverridesAttribute(constraint = PositiveOrZero.class, name = "message")
    String message() default "Multiplo invalido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int number();
}
