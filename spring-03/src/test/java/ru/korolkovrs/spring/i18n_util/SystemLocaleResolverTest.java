package ru.korolkovrs.spring.i18n_util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SystemLocalResolver")
class SystemLocaleResolverTest {

    @Test
    @DisplayName("Should return the system locale")
    void shouldReturnSystemLocal() {
        assertEquals(Locale.getDefault(), new SystemLocaleResolver().getLocale());
    }

}