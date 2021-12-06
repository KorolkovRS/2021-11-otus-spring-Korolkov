package ru.korolkovrs.spring.provider;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.korolkovrs.spring.i18n_util.ResourcePathResolver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("ResourceProviderServiceImpl")
@ExtendWith(MockitoExtension.class)
public class ResourceProviderImplTest {
    @InjectMocks
    private ResourceProviderImpl resourceProvider;

    @Mock
    private ResourcePathResolver pathResolver;

    @DisplayName("Opens the file from the resource correctly")
    @Test
    void shouldProvideAccessToResource() {
        given(pathResolver.getResourcePath(Locale.ENGLISH)).willReturn("questions.csv");

        try (InputStream is = resourceProvider.getResourceStream(Locale.ENGLISH)) {
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
        given(pathResolver.getResourcePath(Locale.ENGLISH)).willReturn("incorrect_name.csv");

        assertThrows(RuntimeException.class,
                () -> {
                    InputStream is = resourceProvider.getResourceStream(Locale.ENGLISH);
                    is.close();
                });
    }

    @DisplayName("The resource passed by the method is closed correctly")
    @Test
    void shouldReturnClosedResource() {
        given(pathResolver.getResourcePath(Locale.ENGLISH)).willReturn("questions.csv");
        assertDoesNotThrow(() -> resourceProvider.getResourceStream(Locale.ENGLISH).close());
    }

    @DisplayName("Must request the path to resources from ResourcePathResolver")
    @Test
    void shouldRequestResourcePath() {
        given(pathResolver.getResourcePath(Locale.ENGLISH)).willReturn("questions.csv");
        resourceProvider.getResourceStream(Locale.ENGLISH);
        verify(pathResolver, times(1)).getResourcePath(any());
    }
}

