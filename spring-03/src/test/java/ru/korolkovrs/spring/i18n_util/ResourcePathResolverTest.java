package ru.korolkovrs.spring.i18n_util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ResourcePathResolver")
@ExtendWith(MockitoExtension.class)
class ResourcePathResolverTest {
    private Map<String, String> codes;

    @InjectMocks
    private ResourcePathResolver pathResolver;

    @BeforeEach
    void init() {
        codes = new HashMap<>();
        codes.put("en", "test_questions.csv");
        codes.put("ru", "ru_test_questions.csv");
        pathResolver.setCodes(codes);
    }

    @Test
    @DisplayName("Should return the path to the file with questions according to the locale")
    void shouldReturnCorrectPathToCSV() {
        assertAll(
                () -> assertEquals(pathResolver.getResourcePath(), codes.get("ru")),
                () -> assertNotEquals(pathResolver.getResourcePath(), codes.get("en"))
        );
    }
}