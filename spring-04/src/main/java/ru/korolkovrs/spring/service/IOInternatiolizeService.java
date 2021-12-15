package ru.korolkovrs.spring.service;

public interface IOInternatiolizeService {
    String input();
    void out(String s, Object... args);
    void outWithInternalize(String s, Object... args);
}
