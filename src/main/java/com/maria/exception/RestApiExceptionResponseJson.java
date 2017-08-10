package com.maria.exception;

import com.maria.model.http.HttpResponseCode;

/**
 * Created on 8/10/2017.
 */
public class RestApiExceptionResponseJson {
    private String message;
    private HttpResponseCode code;

    public String getMessage() {
        return message;
    }

    public RestApiExceptionResponseJson setMessage(String message) {
        this.message = message;
        return this;
    }

    public HttpResponseCode getCode() {
        return code;
    }

    public RestApiExceptionResponseJson setCode(HttpResponseCode code) {
        this.code = code;
        return this;
    }
}
