package com.my.repairagency.exception;

public class DAOException extends Exception {

    private static final String DEFAULT_MESSAGE = "DAO exception.";

    public DAOException() {
    }

    public DAOException(String message) {
        super(message == null ? DEFAULT_MESSAGE : DEFAULT_MESSAGE + " " + message);
    }
}
