package com.paylater.user_service.exception;

public class UserNotFound extends Exception {
    public UserNotFound(String s) {
        super(s);
    }

    public UserNotFound() {
        super("User does not exist");
    }
}