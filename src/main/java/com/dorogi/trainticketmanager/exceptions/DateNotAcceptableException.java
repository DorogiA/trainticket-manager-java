package com.dorogi.trainticketmanager.exceptions;

public class DateNotAcceptableException extends RuntimeException {
    public DateNotAcceptableException(String dates) {
        super(dates + " are not acceptable as a date");
    }
}
