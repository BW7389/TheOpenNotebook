package com.dev.notebook.exceptions;

public class ApiException extends RuntimeException{

    public ApiException(String message) {
        super(message);
    }
}
