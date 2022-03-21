package ru.korolkovrs.spring23.exception;

public class IllegalResponseDataException extends RuntimeException {
    public IllegalResponseDataException() {}

    public IllegalResponseDataException(String message) {
        super(message);
    }
}
