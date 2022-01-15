package ru.korolkovrs.spring11.factory;

import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class ConsoleInputStreamFactory implements InputStreamFactory {
    @Override
    public InputStream getInputStream() {
        return System.in;
    }
}
