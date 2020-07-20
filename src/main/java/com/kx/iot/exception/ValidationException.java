package com.kx.iot.exception;

import com.kx.iot.response.StatusCode;

public class ValidationException extends RuntimeException {
    private StatusCode statusCode;

    public ValidationException(int code, String msg) {
        statusCode = new StatusCode();
        statusCode.setCode(code);
        statusCode.setMessage(msg);
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }
}
