package ru.korolkovrs.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("ResourceProviderServiceImpl")
public class ResourceProviderServiceImplTest {

    @DisplayName("Opens the file from the resource correctly")
    @Test
    void shouldProvideAccessToResource() {
        ResourceProviderServiceImpl resourceProviderService = new ResourceProviderServiceImpl("csv/questions.csv");
        try (Reader reader = resourceProviderService.getResourceReader()) {
            assertThat(reader)
                    .isNotNull()
                    .isInstanceOf(Reader.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("Throws an error if the file is not in the resources")
    @Test
    void shouldThrowsExceptionIfFileNotFound() {
        ResourceProviderServiceImpl resourceProviderService = new ResourceProviderServiceImpl("csv/incorrect_name.csv");
        assertThrows(RuntimeException.class,
                () -> {
                    Reader reader = resourceProviderService.getResourceReader();
                    reader.close();
                });
    }
}
