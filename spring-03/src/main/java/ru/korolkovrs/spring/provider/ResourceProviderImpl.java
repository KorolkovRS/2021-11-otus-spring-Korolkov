package ru.korolkovrs.spring.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring.exception.QuestionLoadingException;
import ru.korolkovrs.spring.i18n_util.PropertiesFileResourcePathResolver;

import java.io.InputStream;

@Component
@RequiredArgsConstructor
public class ResourceProviderImpl implements ResourceProvider {
    private final PropertiesFileResourcePathResolver pathResolver;

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
