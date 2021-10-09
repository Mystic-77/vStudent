package com.oosd.vstudent.errors;

public class InvalidEndpointException extends RuntimeException {
    public InvalidEndpointException(String message) {
        super(message);
    }

    public InvalidEndpointException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEndpointException(Throwable cause) {
        super(cause);
    }
}
