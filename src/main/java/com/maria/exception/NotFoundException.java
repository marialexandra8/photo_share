package com.maria.exception;

/**
 * Created   on 8/9/2017.
 */
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
