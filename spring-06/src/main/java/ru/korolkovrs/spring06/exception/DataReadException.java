package ru.korolkovrs.spring06.exception;

public class DataReadException extends RuntimeException {
    public DataReadException(Throwable cause) {
        super(cause);
    }

    public DataReadException(String message) {
        super(message);
    }

    public DataReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
