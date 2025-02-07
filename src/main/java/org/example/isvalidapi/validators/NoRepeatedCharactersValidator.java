package org.example.isvalidapi.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.NoArgsConstructor;
import org.example.isvalidapi.annotations.NoRepeatedCharacters;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@NoArgsConstructor
public class NoRepeatedCharactersValidator implements ConstraintValidator<NoRepeatedCharacters, String>, PasswordRuleValidator {

    public final static String MESSAGE = "The password cannot contain repeated characters.";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return getValidationMessage(value) == null;
    }

    public String getValidationMessage(String value) {
        if (value == null) {
            return "Password cannot be null.";
        }

        Set<Character> uniqueChars = new HashSet<>();
        for (char c : value.toCharArray()) {
            if (!uniqueChars.add(c)) {
                return MESSAGE;
            }
        }
        return null;
    }

}
