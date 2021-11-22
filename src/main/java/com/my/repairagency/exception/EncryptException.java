package com.my.repairagency.exception;

public class EncryptException extends Exception{

    private static final String DEFAULT_MESSAGE = "Error during encryption.";

    public EncryptException() {
    }

    public EncryptException(String message) {
        super(message == null ? DEFAULT_MESSAGE : DEFAULT_MESSAGE + " " + message);
    }
}
