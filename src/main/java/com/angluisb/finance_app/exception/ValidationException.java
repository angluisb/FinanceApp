package com.angluisb.finance_app.exception;

public class ValidationException extends  RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
