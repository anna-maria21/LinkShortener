package com.example.linkshortener.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LinkShortenerControllerAdvice {

    @ExceptionHandler(HashingUrlException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError hashUrlException(HashingUrlException ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(WrongUrlException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError wrongUrlException(WrongUrlException ex) {
        return new ApiError(ex.getMessage());
    }
}
