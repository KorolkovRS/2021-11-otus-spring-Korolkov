package ru.korolkovrs.spring.i18n_util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Primary
public class PropertiesFileLocaleResolver implements LocaleContext {
    private Locale locale;

    public PropertiesFileLocaleResolver(@Value("${localeResolver.language}") String language, @Value("${localeResolver.country}") String country) {
        this.locale = new Locale(language, country);
    }

    @Override
    public Locale getLocale() {
        return locale;
    }
}
