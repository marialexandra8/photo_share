package com.maria.exception;

/**
 * Created on 8/16/2017.
 */
public class PermissionDeniedException extends RuntimeException {
    public PermissionDeniedException(String message) {
        super(message);
    }
}
