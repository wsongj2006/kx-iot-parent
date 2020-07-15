package com.kx.iot.response;

import lombok.Data;

@Data
public class HttpResponse<T> {
    private StatusCode statusCode;

    private T data;

    public static HttpResponse create() {
        return new HttpResponse();
    }

    public HttpResponse buildStatus(Integer code, String msg) {
        StatusCode statusCode = new StatusCode();
        statusCode.setCode(code);
        statusCode.setMessage(msg);
        this.setStatusCode(statusCode);
        return this;
    }

    public HttpResponse buildStatus(ResponseCode responseCode) {
        StatusCode statusCode = new StatusCode();
        statusCode.setCode(responseCode.getCode());
        statusCode.setMessage(responseCode.getDescription());
        this.setStatusCode(statusCode);
        return this;
    }

    public HttpResponse buildStatus(StatusCode statusCode) {
        this.setStatusCode(statusCode);
        return this;
    }

}
