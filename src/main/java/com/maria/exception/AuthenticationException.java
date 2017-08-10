package com.maria.exception;

/**
 * Created   on 8/9/2017.
 */
public class AuthenticationException extends org.springframework.security.core.AuthenticationException {
    public AuthenticationException(String message) {
        super(message);
    }
}
