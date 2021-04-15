package com.paylater.merchant_service.exception;

public class MerchantAlreadyExists extends Exception {
    public MerchantAlreadyExists(String s) {
        super(s);
    }

    public MerchantAlreadyExists() {
        super("User already exists");
    }
}