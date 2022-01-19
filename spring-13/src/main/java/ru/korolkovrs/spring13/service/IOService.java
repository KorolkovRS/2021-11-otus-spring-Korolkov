package ru.korolkovrs.spring13.service;

public interface IOService {
    String input();

    void out(String s, Object... args);
}
