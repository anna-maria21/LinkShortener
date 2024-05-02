package com.example.linkshortener.exceptions;

public class WrongUrlException extends RuntimeException {
    public WrongUrlException() {
        super("Invalid URL");
    }
}
