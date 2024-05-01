package com.dorogi.trainticketmanager.exceptions;

public class EmailAlreadyPresentException extends RuntimeException {
    public EmailAlreadyPresentException(String email) {
        super("This email is already taken: " + email);
    }
}
