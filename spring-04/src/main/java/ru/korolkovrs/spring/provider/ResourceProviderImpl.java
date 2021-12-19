package ru.korolkovrs.spring.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring.exception.QuestionLoadingException;
import ru.korolkovrs.spring.i18n_util.PropertiesFileResourcePathResolver;
import ru.korolkovrs.spring.i18n_util.ResourcePathResolver;

import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class ResourceProviderImpl implements ResourceProvider {
    private final ResourcePathResolver pathResolver;

    @Override
    public InputStream getResourceStream() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream is = classLoader.getResourceAsStream(pathResolver.getResourcePath());
        if (is == null) {
            throw new QuestionLoadingException("File not found! " + pathResolver.getResourcePath());
        }
        return is;
    }
}
