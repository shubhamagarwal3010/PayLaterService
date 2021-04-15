package com.paylater.payment_service.exception;

public class TransactionFailedException extends Exception {
    public TransactionFailedException(String s) {
        super(s);
    }
}