package org.example.isvalidapi.domains.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidPasswordException extends RuntimeException {

    private final List<String> errors;

    public InvalidPasswordException(List<String> errors) {
        super("Password is invalid.");
        this.errors = errors;
    }

}
