package org.example.isvalidapi.controllers;

import org.example.isvalidapi.domains.dtos.PasswordValidateResDTO;
import org.example.isvalidapi.domains.dtos.base.ResponseDTO;
import org.example.isvalidapi.domains.exceptions.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ResponseDTO<PasswordValidateResDTO>> handleInvalidPasswordException(InvalidPasswordException ex) {
        ResponseDTO<PasswordValidateResDTO> response = ResponseDTO.<PasswordValidateResDTO>builder()
                .message(ex.getMessage())
                .errors(ex.getErrors())
                .content(PasswordValidateResDTO.builder().isValid(false).build())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<PasswordValidateResDTO>> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        ResponseDTO<PasswordValidateResDTO> response = ResponseDTO.<PasswordValidateResDTO>builder()
                .message("Password is invalid.")
                .errors(errors)
                .content(PasswordValidateResDTO.builder().isValid(false).build())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<Void>> handleGenericException(Exception ex) {
        ResponseDTO<Void> response = ResponseDTO.<Void>builder()
                .message("Internal server error")
                .errors(List.of(ex.getMessage()))
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
