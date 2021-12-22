package ru.korolkovrs.spring.i18n_util;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.i18n.LocaleContext;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@RequiredArgsConstructor
@DisplayName("Internalizer test with context")
public class InternalizerSpringBootTest {
    @MockBean
    private LocaleContext localeContext;

    @Autowired
    private Internalizer internalizer;

    @Test
    @DisplayName("Возвращает интернализированое значение строки на английском")
    void shouldReturnCorrectEnglishMessage() {
        given(localeContext.getLocale()).willReturn(new Locale("en"));
        assertEquals(internalizer.internalizeMessage("internalizer.string"), "english");
    }

    @Test
    @DisplayName("Возвращает интернализированое значение строки на французком")
    void shouldReturnCorrectFranceMessage() {
        given(localeContext.getLocale()).willReturn(new Locale("fr"));
        assertEquals(internalizer.internalizeMessage("internalizer.string"), "france");
    }

    @Test
    @DisplayName("Возвращает дефолтное значение строки")
    void shouldReturnCorrectDefaultMessage() {
        given(localeContext.getLocale()).willReturn(new Locale("de"));
        assertEquals(internalizer.internalizeMessage("internalizer.string"), "default");
    }
}
