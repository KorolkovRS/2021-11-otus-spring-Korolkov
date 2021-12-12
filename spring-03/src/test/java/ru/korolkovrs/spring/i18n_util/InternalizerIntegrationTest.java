package ru.korolkovrs.spring.i18n_util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.util.Locale;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Internalizer")
@SpringBootTest
class InternalizerIntegrationTest {
    @Autowired
    private ResourcePathResolver localeResolver;
    @Autowired
    private Internalizer internalizer;
    @Autowired
    private MessageSource messageSource;

    @Test
    @DisplayName("Should request a locale from LocaleContext")
    void shouldRequestLocal() {
        internalizer.internalizeMessage("internalizer.string");
//        verify(localeResolver, times(1)).getLocale();
    }

    @Test
    @DisplayName("Should request a locale from LocaleContext")
    void shouldIntenalizeMesasge() {
        internalizer.internalizeMessage("internalizer.string");
        assertEquals(internalizer.internalizeMessage("internalizer.string"), "english");
    }

    @Test
    @DisplayName("If there is no resource with the required locale, use the default setting")
    void shouldInternalizeDefault() {
        internalizer.internalizeMessage("internalizer.string");
        assertEquals(internalizer.internalizeMessage("internalizer.string"), "default");
    }
}