package ru.korolkovrs.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.korolkovrs.spring.domain.User;
import ru.korolkovrs.spring.i18n_util.Internalizer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("UserServiceImpl")
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private final static String NAME = "Ivan";
    private final static String SURNAME = "Ivanov";

    @Mock
    private IOServiceImpl ioService;

    @Mock
    private Internalizer internalizer;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void init() {
        when(ioService.input())
                .thenReturn(NAME)
                .thenReturn(SURNAME);
        given(internalizer.internalizeMessage(anyString())).willReturn("test string");
    }

    @Test
    @DisplayName("Should сorrectly create user")
    void shouldCorrectSetUser() {
        User user = userService.getUser();
        assertAll(
                () -> assertEquals(user.getName(), NAME),
                () -> assertEquals(user.getSurname(), SURNAME)
        );
    }

    @Test
    @DisplayName("Should request the first and last name in an internalized form")
    void ShouldRequestNameAndSurname() {
        userService.getUser();
        assertAll(
                () -> verify(ioService, times(2)).out("test string"),
                () -> verify(internalizer, times(2)).internalizeMessage(any())
        );
    }
}