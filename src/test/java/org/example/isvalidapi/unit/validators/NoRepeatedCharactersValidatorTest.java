package org.example.isvalidapi.unit.validators;

import org.example.isvalidapi.validators.NoRepeatedCharactersValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NoRepeatedCharactersValidatorTest {

    @InjectMocks
    private NoRepeatedCharactersValidator validator;

    @Test
    void shouldReturnTrueWhenPasswordHasUniqueCharacters() {
        assertTrue(validator.isValid("Abc123!@", null));
    }

    @Test
    void shouldReturnFalseWhenPasswordHasRepeatedCharacters() {
        assertFalse(validator.isValid("AA123!@", null)); // "A" está repetido
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
        assertEquals(NoRepeatedCharactersValidator.MESSAGE, validator.getValidationMessage("AA123!@"));
    }

    @Test
    void shouldReturnErrorMessageForNullPassword() {
        assertEquals("Password cannot be null.", validator.getValidationMessage(null));
    }

}