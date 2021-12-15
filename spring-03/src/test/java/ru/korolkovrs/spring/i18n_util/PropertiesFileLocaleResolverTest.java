package ru.korolkovrs.spring.i18n_util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("SystemLocalResolver")
class PropertiesFileLocaleResolverTest {

    @Test
    @DisplayName("Should return the locale from properties")
    void shouldReturnLocal() {
        PropertiesFileLocaleResolver localeResolver = new PropertiesFileLocaleResolver("de", "DE");
        assertEquals(localeResolver.getLocale(), new Locale("de", "DE"));
    }

}