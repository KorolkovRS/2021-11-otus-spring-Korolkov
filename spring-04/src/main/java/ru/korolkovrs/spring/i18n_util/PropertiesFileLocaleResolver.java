package ru.korolkovrs.spring.i18n_util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class PropertiesFileLocaleResolver implements LocaleContext {
    private Locale locale;

    public PropertiesFileLocaleResolver(@Value("${locale-resolver.default-language}") String language) {
        this.locale = new Locale(language);
    }

    @Override
    public Locale getLocale() {
        return locale;
    }
}
