package ru.korolkovrs.spring.i18n_util;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@RequiredArgsConstructor
@DisplayName("Internalizer test with context")
public class InternalizerSpringBootTest {
    @MockBean
    private PropertiesFileLocaleResolver localeResolver;

    @Autowired
    private Internalizer internalizer;

    @Test
    @DisplayName("Возвращает интернализированое значение строки в соответствии с локалью")
    void shouldReturnCorrectMessage() {
        String message = "internalizer.string";
        String internationalizeMessage = "english";
        Locale locale = new Locale("en");

        given(localeResolver.getLocale()).willReturn(locale);

        assertEquals(internalizer.internalizeMessage(message), internationalizeMessage);
    }
}
