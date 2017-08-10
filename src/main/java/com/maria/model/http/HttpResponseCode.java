package com.maria.model.http;

/**
 * Created on 8/10/2017.
 */
public enum HttpResponseCode {
    UNAUTHORIZED(403);

    private int code;

    HttpResponseCode(int code) {
        this.code = code;
    }
}
