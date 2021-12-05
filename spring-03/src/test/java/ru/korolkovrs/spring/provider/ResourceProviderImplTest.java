package ru.korolkovrs.spring.provider;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("ResourceProviderServiceImpl")
public class ResourceProviderImplTest {

    @DisplayName("Opens the file from the resource correctly")
    @Test
    void shouldProvideAccessToResource() {
        ResourceProviderImpl resourceProviderService = new ResourceProviderImpl("questions.csv");
        try (InputStream is = resourceProviderService.getResourceStream()) {
            assertThat(is)
                    .isNotNull()
                    .isInstanceOf(InputStream.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("Throws an error if the file is not in the resources")
    @Test
    void shouldThrowsExceptionIfFileNotFound() {
        ResourceProviderImpl resourceProviderService = new ResourceProviderImpl("incorrect_name.csv");
        assertThrows(RuntimeException.class,
                () -> {
                    InputStream is = resourceProviderService.getResourceStream();
                    is.close();
                });
    }

    @DisplayName("The resource passed by the method is closed correctly")
    @Test
    void shouldReturnClosedResource() {
        ResourceProviderImpl resourceProviderService = new ResourceProviderImpl("questions.csv");
        assertDoesNotThrow(() -> resourceProviderService.getResourceStream().close());
    }
}
