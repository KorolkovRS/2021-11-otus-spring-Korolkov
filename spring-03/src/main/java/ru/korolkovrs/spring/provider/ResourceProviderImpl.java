package ru.korolkovrs.spring.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring.exception.QuestionLoadingException;
import ru.korolkovrs.spring.i18n_util.ResourcePathResolver;

import java.io.InputStream;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class ResourceProviderImpl implements ResourceProvider {
    private final ResourcePathResolver pathResolver;

    @Override
    public InputStream getResourceStream(Locale locale) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream is = classLoader.getResourceAsStream(pathResolver.getResourcePath(locale));
        if (is == null) {
            throw new QuestionLoadingException("File not found! " + pathResolver.getResourcePath(locale));
        }
        return is;
    }
}
