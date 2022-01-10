package ru.korolkovrs.spring06.factory;

import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class ConsoleInputStreamFactory implements InputStreamFactory {
    @Override
    public InputStream getInputStream() {
        return System.in;
    }
}
