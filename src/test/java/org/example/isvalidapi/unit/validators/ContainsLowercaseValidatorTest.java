package org.example.isvalidapi.unit.validators;

import org.example.isvalidapi.validators.ContainsLowercaseValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ContainsLowercaseValidatorTest {

    @InjectMocks
    private ContainsLowercaseValidator validator;

    @Test
    void shouldReturnTrueWhenPasswordContainsLowercase() {
        assertTrue(validator.isValid("ABCd123!", null));
    }

    @Test
    void shouldReturnFalseWhenPasswordDoesNotContainLowercase() {
        assertFalse(validator.isValid("ABC123!", null));
    }

    @Test
    void shouldReturnFalseWhenPasswordIsNull() {
        assertFalse(validator.isValid(null, null));
    }

    @Test
    void shouldReturnNullMessageForValidPassword() {
        assertNull(validator.getValidationMessage("ABCd123!"));
    }

    @Test
    void shouldReturnErrorMessageForInvalidPassword() {
        assertEquals(ContainsLowercaseValidator.MESSAGE, validator.getValidationMessage("ABC123!"));
    }

    @Test
    void shouldReturnErrorMessageForNullPassword() {
        assertEquals(ContainsLowercaseValidator.MESSAGE, validator.getValidationMessage(null));
    }

}