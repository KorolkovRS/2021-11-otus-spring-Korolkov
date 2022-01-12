package ru.korolkovrs.spring11.service;

public interface IOService {
    String input();

    void out(String s, Object... args);
}
