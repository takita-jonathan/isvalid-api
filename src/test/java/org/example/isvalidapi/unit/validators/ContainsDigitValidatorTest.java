package org.example.isvalidapi.unit.validators;

import org.example.isvalidapi.validators.ContainsDigitValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ContainsDigitValidatorTest {

    @InjectMocks
    private ContainsDigitValidator validator;

    @Test
    void shouldReturnTrueWhenPasswordContainsDigit() {
        assertTrue(validator.isValid("abc123", null));
    }

    @Test
    void shouldReturnFalseWhenPasswordDoesNotContainDigit() {
        assertFalse(validator.isValid("abcdef", null));
    }

    @Test
    void shouldReturnFalseWhenPasswordIsNull() {
        assertFalse(validator.isValid(null, null));
    }

    @Test
    void shouldReturnNullMessageForValidPassword() {
        assertNull(validator.getValidationMessage("abc123"));
    }

    @Test
    void shouldReturnErrorMessageForInvalidPassword() {
        assertEquals(ContainsDigitValidator.MESSAGE, validator.getValidationMessage("abcdef"));
    }

    @Test
    void shouldReturnErrorMessageForNullPassword() {
        assertEquals(ContainsDigitValidator.MESSAGE, validator.getValidationMessage(null));
    }

}