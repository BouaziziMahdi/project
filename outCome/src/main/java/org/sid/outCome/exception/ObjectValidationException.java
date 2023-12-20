package org.sid.outCome.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
public class ObjectValidationException extends RuntimeException {

    private final Set<String> violations;

    private final String violationSource;
}