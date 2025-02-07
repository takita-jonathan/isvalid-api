package org.example.isvalidapi.unit.services;

import org.example.isvalidapi.domains.exceptions.InvalidPasswordException;
import org.example.isvalidapi.services.PasswordValidatorService;
import org.example.isvalidapi.validators.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class PasswordValidatorServiceTest {

    @Spy private MinLengthValidator minLengthValidator;
    @Spy private ContainsDigitValidator containsDigitValidator;
    @Spy private ContainsLowercaseValidator containsLowercaseValidator;
    @Spy private ContainsUppercaseValidator containsUppercaseValidator;
    @Spy private ContainsSpecialCharacterValidator containsSpecialCharacterValidator;
    @Spy private NoRepeatedCharactersValidator noRepeatedCharactersValidator;

    private PasswordValidatorService service;

    @BeforeEach
    void setUp() {
        service = new PasswordValidatorService(
                List.of(
                        minLengthValidator,
                        containsDigitValidator,
                        containsLowercaseValidator,
                        containsUppercaseValidator,
                        containsSpecialCharacterValidator,
                        noRepeatedCharactersValidator
                )
        );
    }

    @Test
    void shouldValidateCorrectPassword() {
        String validPassword = "Aa1!@#xyz";
        assertThat(service.getValidationErrors(validPassword)).isEmpty();
    }

    @ParameterizedTest
    @CsvSource({
            "'', false",
            "aa, false",
            "ab, false",
            "AAAbbbCc, false",
            "AbTp9!foo, false",
            "AbTp9!foA, false",
            "AbTp9 fok, false",
            "AbTp9!fok, true",
            "AbTp9! fok, true",
            "'   ', false",
            "Aa1!@#xyz, true",
            "Aa1!, false",
            "Aabcdef!@#, false",
            "A123!@#XYZ, false",
            "a123!@#xyz, false",
            "Aa1234567, false",
            "Aa1!Aa1!, false",
            "' Aa1!@#xyz', true",
            "'Aa1!@#xyz ', true",
            "' A a 1 ! @ # x y z ', false"
    })
    void shouldValidatePasswordCorrectly(String password, boolean expectedValid) {
        boolean isValid = service.getValidationErrors(password).isEmpty();
        assertThat(isValid).isEqualTo(expectedValid);
    }

    @ParameterizedTest
    @CsvSource({
            "Aa1!, The password must be at least 9 characters long.",
            "Aabcdef!@#, The password must have at least one number.",
            "A123!@#XYZ, The password must have at least one lowercase letter.",
            "a123!@#xyz, The password must have at least one uppercase letter.",
            "Aa1234567, The password must have at least one special character.",
            "Aa1!Aa1!, The password cannot contain repeated characters."
    })
    void shouldDetectSpecificErrors(String password, String expectedError) {
        List<String> errors = service.getValidationErrors(password);
        assertThat(errors).contains(expectedError);
    }

    @Test
    void shouldDetectMultipleErrors() {
        String invalidPassword = "aaaaaaa";
        List<String> errors = service.getValidationErrors(invalidPassword);

        assertThat(errors).containsExactlyInAnyOrder(
                "The password must be at least 9 characters long.",
                "The password must have at least one number.",
                "The password must have at least one uppercase letter.",
                "The password must have at least one special character.",
                "The password cannot contain repeated characters."
        );
    }

    @Test
    void shouldThrowExceptionForInvalidPassword() {
        String invalidPassword = "abc123";

        assertThatThrownBy(() -> service.validatePassword(invalidPassword))
                .isInstanceOf(InvalidPasswordException.class)
                .hasMessage("Password is invalid.")
                .satisfies(ex -> assertThat(((InvalidPasswordException) ex).getErrors())
                        .contains("The password must be at least 9 characters long."));
    }



}