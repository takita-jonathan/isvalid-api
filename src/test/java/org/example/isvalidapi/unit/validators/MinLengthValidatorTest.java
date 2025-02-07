package org.example.isvalidapi.unit.validators;

import org.example.isvalidapi.annotations.MinLength;
import org.example.isvalidapi.validators.MinLengthValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MinLengthValidatorTest {

    @InjectMocks
    private MinLengthValidator validator;

    @Mock
    private MinLength minLengthAnnotation;

    @BeforeEach
    void setUp() {
        Mockito.when(minLengthAnnotation.value()).thenReturn(9);
        validator.initialize(minLengthAnnotation);
    }

    @Test
    void shouldReturnTrueWhenPasswordMeetsMinLength() {
        assertTrue(validator.isValid("Abc123!@#", null)); // 9 caracteres
        assertTrue(validator.isValid("A1!abcdefghij", null)); // 12 caracteres
    }

    @Test
    void shouldReturnFalseWhenPasswordIsShorterThanMinLength() {
        assertFalse(validator.isValid("Abc123!", null)); // 7 caracteres
        assertFalse(validator.isValid("12345678", null)); // 8 caracteres
    }

    @Test
    void shouldReturnFalseWhenPasswordIsNull() {
        assertFalse(validator.isValid(null, null));
    }

    @Test
    void shouldReturnNullMessageForValidPassword() {
        assertNull(validator.getValidationMessage("Abc123!@#"));
    }

    @Test
    void shouldReturnErrorMessageForInvalidPassword() {
        assertEquals("The password must be at least 9 characters long.", validator.getValidationMessage("Abc12!"));
    }

    @Test
    void shouldReturnErrorMessageForNullPassword() {
        assertEquals("The password must be at least 9 characters long.", validator.getValidationMessage(null));
    }

    @Test
    void shouldReturnErrorMessageWithCorrectLengthSubstitution() {
        Mockito.when(minLengthAnnotation.value()).thenReturn(12);
        validator.initialize(minLengthAnnotation);
        assertEquals("The password must be at least 12 characters long.", validator.getValidationMessage("123456789"));
    }

}