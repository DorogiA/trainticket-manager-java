package com.dorogi.trainticketmanager.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
/*
    @ExceptionHandler(value = {

    })
    protected ResponseEntity<Object> handleBadRequest(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(value = {

    })
    protected ResponseEntity<Object> handleNotFound(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatusCode.valueOf(404));
    }*/

    @ExceptionHandler(value = {
            EmailAlreadyPresentException.class
    })
    protected ResponseEntity<Object> handleConflict(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatusCode.valueOf(409));
    }
}