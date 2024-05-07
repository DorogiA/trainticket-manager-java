package com.dorogi.trainticketmanager.exceptions;

public class NotEnoughBalanceException extends RuntimeException {
    public NotEnoughBalanceException(String id) {
        super("Not enough balance on customer with ID " + id);
    }
}
