package ru.korolkovrs.spring20.exception;

public class IllegalResponseDataException extends RuntimeException {
    public IllegalResponseDataException() {}

    public IllegalResponseDataException(String message) {
        super(message);
    }
}
