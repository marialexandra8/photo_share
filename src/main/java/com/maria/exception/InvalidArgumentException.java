package com.maria.exception;

/**
 * Created on 8/19/2017.
 */
public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String message) {
        super(message);
    }
}
