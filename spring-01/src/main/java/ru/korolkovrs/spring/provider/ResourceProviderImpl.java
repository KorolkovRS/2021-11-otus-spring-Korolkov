package ru.korolkovrs.spring.provider;

import lombok.RequiredArgsConstructor;

import java.io.InputStream;

@RequiredArgsConstructor
public class ResourceProviderImpl implements ResourceProvider {
    private final String fileName;

    @Override
    public InputStream getResourceStream() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream is = classLoader.getResourceAsStream(fileName);
        if (is == null) {
            throw new RuntimeException("File not found! " + fileName);
        }
        return is;
    }
}
