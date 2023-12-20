package com.projectIntegration.authentification.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OperationNonPermittedException extends RuntimeException {
    public OperationNonPermittedException(String message) {
        super(message);
    }
}
