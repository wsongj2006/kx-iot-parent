package com.kx.iot.exception;

import com.kx.iot.response.HttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class ValidationExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public HttpResponse handException(HttpServletRequest request, ValidationException e) {
        return HttpResponse.create()
                .buildStatus(e.getStatusCode());
    }
}
