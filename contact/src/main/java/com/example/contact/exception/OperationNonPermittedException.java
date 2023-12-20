package com.example.contact.exception;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OperationNonPermittedException extends RuntimeException {
    public OperationNonPermittedException(String message) {
        super( message );
    }
}