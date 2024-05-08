package com.dorogi.trainticketmanager.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            IdNotAcceptableException.class
    })
    protected ResponseEntity<Object> handleBadRequest(RuntimeException exception) {
        log.info(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(value = {
            NoEntityFoundException.class
    })
    protected ResponseEntity<Object> handleNotFound(RuntimeException exception) {
        log.info(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(value = {
            NotEnoughBalanceException.class,
            DateNotAcceptableException.class
    })
    protected ResponseEntity<Object> handleNotAllowed(RuntimeException exception) {
        log.info(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatusCode.valueOf(405));
    }

    @ExceptionHandler(value = {
            EmailAlreadyPresentException.class
    })
    protected ResponseEntity<Object> handleConflict(RuntimeException exception) {
        log.info(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatusCode.valueOf(409));
    }
}
