package com.example.simplewebquiz.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found !");
    }
}
