package com.paylater.payment_service.exception;

public class MerchantNotFound extends Exception {
    public MerchantNotFound(String s) {
        super(s);
    }

    public MerchantNotFound() {
        super("Merchant does not exist");
    }
}