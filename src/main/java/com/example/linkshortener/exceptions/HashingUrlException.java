package com.example.linkshortener.exceptions;

public class HashingUrlException extends RuntimeException {
    public HashingUrlException() {
        super("Cannot hash provided link!");
    }
}
