package ru.korolkovrs.spring.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring.exception.QuestionLoadingException;

import java.io.InputStream;

@Component
public class ResourceProviderImpl implements ResourceProvider {
    private final String fileName;

    public ResourceProviderImpl(@Value("${fileName}") String fileName) {
        this.fileName = fileName;
    }

    @Override
    public InputStream getResourceStream() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream is = classLoader.getResourceAsStream(fileName);
        if (is == null) {
            throw new QuestionLoadingException("File not found! " + fileName);
        }
        return is;
    }
}
