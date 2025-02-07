package org.example.isvalidapi.services;

import org.example.isvalidapi.domains.exceptions.InvalidPasswordException;
import org.example.isvalidapi.validators.PasswordRuleValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PasswordValidatorService {

    private final List<PasswordRuleValidator> validators;

    public PasswordValidatorService(List<PasswordRuleValidator> validators) {
        this.validators = validators;
    }

    public void validatePassword(String password) {
        List<String> errors = getValidationErrors(password);
        if (!errors.isEmpty()) {
            throw new InvalidPasswordException(errors);
        }
    }

    public List<String> getValidationErrors(String password) {
        if (password == null) {
            return List.of("The password cannot be null.");
        }

        return validators.stream()
                .map(validator -> validator.getValidationMessage(password))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
