package ru.korolkovrs.spring.i18n_util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@DisplayName("ResourcePathResolver")
@ExtendWith(MockitoExtension.class)
class PropertiesFileResourcePathResolverTest {
    private Map<String, String> codes;

    @Mock
    private PropertiesFileLocaleResolver localeContext = mock(PropertiesFileLocaleResolver.class);

    @InjectMocks
    private PropertiesFileResourcePathResolver pathResolver;

    @BeforeEach
    void init() {
            codes = Map.of(
                "en", "test_questions.csv",
                "ru", "ru_test_questions.csv"
        );
        pathResolver.setCodes(codes);
    }

    @Test
    @DisplayName("Should return the path to the file with questions according to the locale")
    void shouldReturnCorrectPathToCSV() {
        given(localeContext.getLocale()).willReturn(new Locale("ru", "RU"));
        assertAll(
                () -> assertEquals(pathResolver.getResourcePath(), codes.get("ru")),
                () -> assertNotEquals(pathResolver.getResourcePath(), codes.get("de"))
        );
    }

    @Test
    @DisplayName("Should request the locale from the localeContext")
    void shouldRequestLocale() {
        given(localeContext.getLocale()).willReturn(new Locale("ru", "RU"));
        pathResolver.getResourcePath();
        verify(localeContext, times(1)).getLocale();
    }

    @Test
    @DisplayName("If the locale is not in properties, return the default path to the file")
    void shouldReturnDefaultPath() {
        given(localeContext.getLocale()).willReturn(new Locale("de", "DE"));
        assertEquals(pathResolver.getResourcePath(), codes.get(pathResolver.getDefaultLocale().getLanguage()));
    }

    @Test
    void setCodesCorrect() {
        pathResolver.setCodes(codes);
        assertEquals(codes, pathResolver.getCodes());
    }
}