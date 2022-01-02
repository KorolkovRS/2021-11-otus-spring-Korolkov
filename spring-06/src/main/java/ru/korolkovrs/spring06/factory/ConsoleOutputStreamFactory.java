package ru.korolkovrs.spring06.factory;

import org.springframework.stereotype.Component;

import java.io.OutputStream;

@Component
public class ConsoleOutputStreamFactory implements OutputStreamFactory {
    @Override
    public OutputStream getOutputStream() {
        return System.out;
    }
}
