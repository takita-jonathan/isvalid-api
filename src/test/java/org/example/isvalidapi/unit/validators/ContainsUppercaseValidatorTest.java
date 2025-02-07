package org.example.isvalidapi.unit.validators;

import org.example.isvalidapi.validators.ContainsUppercaseValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ContainsUppercaseValidatorTest {

    @InjectMocks
    private ContainsUppercaseValidator validator;

    @Test
    void shouldReturnTrueWhenPasswordContainsUppercase() {
        assertTrue(validator.isValid("abcD123!", null));
    }

    @Test
    void shouldReturnFalseWhenPasswordDoesNotContainUppercase() {
        assertFalse(validator.isValid("abc123!", null));
    }

    @Test
    void shouldReturnFalseWhenPasswordIsNull() {
        assertFalse(validator.isValid(null, null));
    }

    @Test
    void shouldReturnNullMessageForValidPassword() {
        assertNull(validator.getValidationMessage("abcD123!"));
    }

    @Test
    void shouldReturnErrorMessageForInvalidPassword() {
        assertEquals(ContainsUppercaseValidator.MESSAGE, validator.getValidationMessage("abc123!"));
    }

    @Test
    void shouldReturnErrorMessageForNullPassword() {
        assertEquals(ContainsUppercaseValidator.MESSAGE, validator.getValidationMessage(null));
    }

}