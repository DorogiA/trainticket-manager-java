package com.dorogi.trainticketmanager.exceptions;

public class IdNotAcceptableException extends RuntimeException {
    public IdNotAcceptableException(String id) {
        super(id + " is not an acceptable number for client ID");
    }
}
