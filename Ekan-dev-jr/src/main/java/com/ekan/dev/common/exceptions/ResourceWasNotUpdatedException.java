package com.ekan.dev.common.exceptions;

import java.io.Serial;

public class ResourceWasNotUpdatedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceWasNotUpdatedException(String message) {
        super(message);
    }
}