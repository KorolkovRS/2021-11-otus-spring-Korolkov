package ru.korolkovrs.spring.i18n_util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Locale;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Internalizer")
class InternalizerTest {
    @Mock
    private SystemLocaleResolver localeResolver = mock(SystemLocaleResolver.class);

    private Internalizer internalizer = new Internalizer(localeResolver, "i18n_test/messages");

    @Test
    @DisplayName("Should request a locale from LocaleContext")
    void shouldRequestLocal() {
        given(localeResolver.getLocale()).willReturn(new Locale("en", "EN"));
        internalizer.internalizeMessage("internalizer.string");
        verify(localeResolver, times(1)).getLocale();
    }

    @Test
    @DisplayName("Should request a locale from LocaleContext")
    void shouldIntenalizeMesasge() {
        given(localeResolver.getLocale()).willReturn(new Locale("en", "EN"));
        internalizer.internalizeMessage("internalizer.string");
        assertEquals(internalizer.internalizeMessage("internalizer.string"), "english");
    }

    @Test
    @DisplayName("If there is no resource with the required locale, use the default setting")
    void shouldInternalizeDefault() {
        given(localeResolver.getLocale()).willReturn(new Locale("de", "DE"));
        internalizer.internalizeMessage("internalizer.string");
        assertEquals(internalizer.internalizeMessage("internalizer.string"), "default");
    }
}
