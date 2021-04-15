package com.paylater.user_service.exception;

public class UserAlreadyExists extends Exception {
    public UserAlreadyExists(String s) {
        super(s);
    }

    public UserAlreadyExists() {
        super("User already exists");
    }
}