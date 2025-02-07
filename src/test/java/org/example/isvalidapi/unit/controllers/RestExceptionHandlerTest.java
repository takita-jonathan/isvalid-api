package org.example.isvalidapi.unit.controllers;

import org.example.isvalidapi.controllers.RestExceptionHandler;
import org.example.isvalidapi.domains.dtos.PasswordValidateResDTO;
import org.example.isvalidapi.domains.dtos.base.ResponseDTO;
import org.example.isvalidapi.domains.exceptions.InvalidPasswordException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestExceptionHandlerTest {

    @InjectMocks
    private RestExceptionHandler exceptionHandler;

    @Test
    void shouldReturnBadRequestForInvalidPasswordException() {
        List<String> errors = List.of("Password must be at least 9 characters long.", "Password must have one special character.");
        InvalidPasswordException ex = new InvalidPasswordException(errors);

        ResponseEntity<ResponseDTO<PasswordValidateResDTO>> response = exceptionHandler.handleInvalidPasswordException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Password is invalid.", response.getBody().getMessage());
        assertEquals(errors, response.getBody().getErrors());
        assertFalse(response.getBody().getContent().getIsValid());
    }

    @Test
    void shouldReturnBadRequestForValidationException() {
        BindException bindException = mock(BindException.class);
        FieldError fieldError1 = new FieldError("password", "password", "Password must be at least 9 characters long.");
        FieldError fieldError2 = new FieldError("password", "password", "Password must contain a special character.");

        when(bindException.getFieldErrors()).thenReturn(List.of(fieldError1, fieldError2));

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindException);

        ResponseEntity<ResponseDTO<PasswordValidateResDTO>> response = exceptionHandler.handleValidationException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Password is invalid.", response.getBody().getMessage());
        assertEquals(List.of("Password must be at least 9 characters long.", "Password must contain a special character."),
                response.getBody().getErrors());
        assertFalse(response.getBody().getContent().getIsValid());
    }

    @Test
    void shouldReturnInternalServerErrorForGenericException() {
        Exception ex = new Exception("Unexpected error occurred.");

        ResponseEntity<ResponseDTO<Void>> response = exceptionHandler.handleGenericException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal server error", response.getBody().getMessage());
        assertEquals(List.of("Unexpected error occurred."), response.getBody().getErrors());
    }

}