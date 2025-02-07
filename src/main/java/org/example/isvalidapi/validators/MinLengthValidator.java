package org.example.isvalidapi.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.isvalidapi.annotations.MinLength;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class MinLengthValidator implements ConstraintValidator<MinLength, String>, PasswordRuleValidator {

    public static final String MESSAGE = "The password must be at least {value} characters long.";
    private int minLength = 9;

    @Override
    public void initialize(MinLength constraintAnnotation) {
        this.minLength = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return getValidationMessage(value) == null;
    }

    public String getValidationMessage(String value) {
        return (value != null && value.length() >= minLength) ? null :
                MESSAGE.replace("{value}", String.valueOf(minLength));
    }

}
