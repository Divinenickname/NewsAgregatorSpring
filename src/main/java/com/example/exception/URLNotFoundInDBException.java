package com.example.exception;

public class URLNotFoundInDBException extends Exception{

    public URLNotFoundInDBException(String message) {
        super(message);
    }
}
