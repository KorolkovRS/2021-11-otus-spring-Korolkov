package ru.korolkovrs.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.Reader;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("ResourceProviderServiceImpl")
public class ResourceProviderServiceImplTest {

    @DisplayName("Opens the file from the resource correctly")
    @Test
    void shouldProvideAccessToResource() {
        ResourceProviderServiceImpl resourceProviderService = new ResourceProviderServiceImpl("csv/questions.csv");
        assertThat(resourceProviderService.getResourceReader())
                .isNotNull()
                .isInstanceOf(Reader.class);
    }

    @DisplayName("Throws an error if the file is not in the resources")
    @Test
    void shouldThrowsExceptionIfFileNotFound() {
        ResourceProviderServiceImpl resourceProviderService = new ResourceProviderServiceImpl("csv/incorrect_name.csv");
        assertThrows(RuntimeException.class,
                () -> resourceProviderService.getResourceReader());
    }
}
