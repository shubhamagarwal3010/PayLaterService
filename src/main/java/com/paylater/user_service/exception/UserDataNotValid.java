package com.paylater.user_service.exception;

public class UserDataNotValid extends Exception {
    public UserDataNotValid(String s) {
        super(s);
    }

    public UserDataNotValid() {
        super("User data is not valid");
    }
}