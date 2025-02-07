package org.example.isvalidapi.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.NoArgsConstructor;
import org.example.isvalidapi.annotations.ContainsUppercase;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ContainsUppercaseValidator implements ConstraintValidator<ContainsUppercase, String>, PasswordRuleValidator {

    public final static String MESSAGE = "The password must have at least one uppercase letter.";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return getValidationMessage(value) == null;
    }

    public String getValidationMessage(String value) {
        return (value != null && value.chars().anyMatch(Character::isUpperCase)) ? null : MESSAGE;
    }

}
