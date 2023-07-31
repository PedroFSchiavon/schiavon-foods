package br.com.schiavon.food.core.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@AllArgsConstructor
@Getter
public class ValidationPatchException extends RuntimeException {
    private BindingResult bindingResult;
}
