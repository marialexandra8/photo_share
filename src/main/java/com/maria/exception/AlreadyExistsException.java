package com.maria.exception;

/**
 * Created on 8/20/2017.
 */
public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
