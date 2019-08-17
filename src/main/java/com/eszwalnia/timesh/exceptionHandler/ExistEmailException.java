package com.eszwalnia.timesh.exceptionHandler;

public class ExistEmailException extends RuntimeException {

    public ExistEmailException(String message) {
        super(message);
    }
}
