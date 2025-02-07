package org.example.isvalidapi.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.NoArgsConstructor;
import org.example.isvalidapi.annotations.ContainsLowercase;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ContainsLowercaseValidator implements ConstraintValidator<ContainsLowercase, String>, PasswordRuleValidator {

    public final static String MESSAGE = "The password must have at least one lowercase letter.";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return getValidationMessage(value) == null;
    }

    public String getValidationMessage(String value) {
        return (value != null && value.chars().anyMatch(Character::isLowerCase)) ? null : MESSAGE;
    }

}
