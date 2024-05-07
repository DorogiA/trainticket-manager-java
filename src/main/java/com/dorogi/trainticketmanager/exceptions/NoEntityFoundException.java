package com.dorogi.trainticketmanager.exceptions;

public class NoEntityFoundException extends RuntimeException {
    public NoEntityFoundException(String credentials) {
        super("There is no customer with this credential: " + credentials);
    }
}
