package org.example.isvalidapi.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.NoArgsConstructor;
import org.example.isvalidapi.annotations.ContainsDigit;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ContainsDigitValidator implements ConstraintValidator<ContainsDigit, String>, PasswordRuleValidator {

    public final static String MESSAGE = "The password must have at least one number.";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return getValidationMessage(value) == null;
    }

    public String getValidationMessage(String value) {
        return (value != null && value.chars().anyMatch(Character::isDigit)) ? null : MESSAGE;
    }
}
