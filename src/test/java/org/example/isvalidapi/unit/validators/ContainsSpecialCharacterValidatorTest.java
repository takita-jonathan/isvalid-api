package org.example.isvalidapi.unit.validators;

import org.example.isvalidapi.validators.ContainsSpecialCharacterValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ContainsSpecialCharacterValidatorTest {

    @InjectMocks
    private ContainsSpecialCharacterValidator validator;

    @Test
    void shouldReturnTrueWhenPasswordContainsSpecialCharacter() {
        assertTrue(validator.isValid("Abc123!@", null));
    }

    @Test
    void shouldReturnFalseWhenPasswordDoesNotContainSpecialCharacter() {
        assertFalse(validator.isValid("Abc123", null));
    }

    @Test
    void shouldReturnFalseWhenPasswordIsNull() {
        assertFalse(validator.isValid(null, null));
    }

    @Test
    void shouldReturnNullMessageForValidPassword() {
        assertNull(validator.getValidationMessage("Abc123!@"));
    }

    @Test
    void shouldReturnErrorMessageForInvalidPassword() {
        assertEquals(ContainsSpecialCharacterValidator.MESSAGE, validator.getValidationMessage("Abc123"));
    }

    @Test
    void shouldReturnErrorMessageForNullPassword() {
        assertEquals(ContainsSpecialCharacterValidator.MESSAGE, validator.getValidationMessage(null));
    }

}