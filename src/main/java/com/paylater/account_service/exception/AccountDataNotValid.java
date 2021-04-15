package com.paylater.account_service.exception;

public class AccountDataNotValid extends Exception {
    public AccountDataNotValid(String s) {
        super(s);
    }

    public AccountDataNotValid() {
        super("Account data is not valid");
    }
}