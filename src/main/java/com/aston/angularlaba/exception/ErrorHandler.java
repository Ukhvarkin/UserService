package com.aston.angularlaba.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler({ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(ValidationException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUserNotFoundException(ValidationException e) {
        return new ErrorResponse(e.getMessage());
    }
}
