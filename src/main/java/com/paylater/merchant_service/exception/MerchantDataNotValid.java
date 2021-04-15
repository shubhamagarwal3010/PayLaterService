package com.paylater.merchant_service.exception;

public class MerchantDataNotValid extends Exception {
    public MerchantDataNotValid(String s) {
        super(s);
    }

    public MerchantDataNotValid() {
        super("User data is not valid");
    }
}