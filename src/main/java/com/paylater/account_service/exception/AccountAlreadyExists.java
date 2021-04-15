package com.paylater.account_service.exception;

public class AccountAlreadyExists extends Exception {
    public AccountAlreadyExists(String s) {
        super(s);
    }

    public AccountAlreadyExists() {
        super("Account already exists");
    }
}