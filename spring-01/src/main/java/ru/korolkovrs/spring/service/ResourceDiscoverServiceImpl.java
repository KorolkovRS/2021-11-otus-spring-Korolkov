package ru.korolkovrs.spring.service;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
public class ResourceDiscoverServiceImpl implements ResourceDiscoverService {
    private final String fileName;

    @Override
    public Reader getResourceReader() {
        try {
            Path path = Paths.get(
                    ClassLoader.getSystemResource(fileName).toURI());
            return Files.newBufferedReader(path);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException("File not found! " + fileName);
        }
    }
}
