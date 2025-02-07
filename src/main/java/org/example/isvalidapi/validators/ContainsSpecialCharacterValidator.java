package org.example.isvalidapi.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.NoArgsConstructor;
import org.example.isvalidapi.annotations.ContainsSpecialCharacter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ContainsSpecialCharacterValidator implements ConstraintValidator<ContainsSpecialCharacter, String>, PasswordRuleValidator {

    public final static String MESSAGE = "The password must have at least one special character.";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_+";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return getValidationMessage(value) == null;
    }

    public String getValidationMessage(String value) {
        return (value != null && value.chars().anyMatch(c -> SPECIAL_CHARACTERS.indexOf(c) >= 0)) ? null : MESSAGE;
    }

}
