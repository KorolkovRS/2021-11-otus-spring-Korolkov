package ru.korolkovrs.spring.i18n_util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.Locale;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Internalizer")
@ExtendWith(MockitoExtension.class)
class InternalizerTest {
    @Mock
    private PropertiesFileLocaleResolver localeResolver = mock(PropertiesFileLocaleResolver.class);

    @Mock
    private MessageSource messageSource = mock(MessageSource.class);

    @InjectMocks
    private Internalizer internalizer;

    @Test
    @DisplayName("Should request a locale from LocaleContext")
    void shouldRequestLocal() {
        given(localeResolver.getLocale()).willReturn(new Locale("en", "EN"));
        internalizer.internalizeMessage("message");

        verify(localeResolver, times(1)).getLocale();
    }

    @Test
    @DisplayName("Should request a message from messageSource")
    void shouldRequestMessage() {
        String message = "message";
        Locale locale = new Locale("en", "EN");
        given(localeResolver.getLocale()).willReturn(locale);
        internalizer.internalizeMessage(message);

        verify(messageSource, times(1)).getMessage(eq(message), any(), eq(locale));
    }

    @Test
    void shouldReturnCorrectMessage() {
        String message = "message";
        String internationalizeMessage = "english message";
        Locale locale = new Locale("en", "EN");

        given(localeResolver.getLocale()).willReturn(locale);
        given(messageSource.getMessage(anyString(), any(), eq(locale))).willReturn(internationalizeMessage);

        assertEquals(internalizer.internalizeMessage(message), internationalizeMessage);
    }
}
