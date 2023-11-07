package com.ekan.dev.common.exceptions;

import java.io.Serial;

public class CouldNotDeleteResourceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CouldNotDeleteResourceException(String message) {
        super(message);
    }
}