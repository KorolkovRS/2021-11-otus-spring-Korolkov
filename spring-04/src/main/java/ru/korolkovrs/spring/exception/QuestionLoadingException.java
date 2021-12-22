package ru.korolkovrs.spring.exception;

public class QuestionLoadingException extends RuntimeException {
    public QuestionLoadingException(String message) {
        super(message);
    }

    public QuestionLoadingException(String message, Throwable e) {
        super(message, e);
    }
}
