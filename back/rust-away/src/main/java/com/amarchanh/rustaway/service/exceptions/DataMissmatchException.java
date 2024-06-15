package com.amarchanh.rustaway.service.exceptions;

public class DataMissmatchException extends RuntimeException {

    public DataMissmatchException() {
    }

    public DataMissmatchException(String message) {
        super(message);
    }
}
