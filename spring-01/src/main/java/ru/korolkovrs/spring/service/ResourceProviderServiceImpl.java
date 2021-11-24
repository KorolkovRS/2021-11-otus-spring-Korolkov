package ru.korolkovrs.spring.service;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
public class ResourceProviderServiceImpl implements ResourceProviderService {
    private final String fileName;

    @Override
    public Reader getResourceReader() {
        ClassLoader classLoader = getClass().getClassLoader();
        Reader reader = new InputStreamReader(classLoader.getResourceAsStream(fileName));
        if (reader == null) {
            throw new RuntimeException("File not found! " + fileName);
        }
        return reader;
    }
}
