package com.paylater.account_service.exception;

public class AccountNotFound extends Exception {
    public AccountNotFound(String s) {
        super(s);
    }

    public AccountNotFound() {
        super("Account does not exist");
    }
}