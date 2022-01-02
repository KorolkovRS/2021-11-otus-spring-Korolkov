package ru.korolkovrs.spring06.service;

public interface IOService {
    String input();

    void out(String s, Object... args);
}
