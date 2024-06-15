package com.amarchanh.rustaway.service.exceptions;

public class UserAlreadyStoredException extends RuntimeException {

    public UserAlreadyStoredException() {
    }

    public UserAlreadyStoredException(String message) {
        super(message);
    }
}
