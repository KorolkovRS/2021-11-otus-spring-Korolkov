package ru.korolkovrs.spring23.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ru.korolkovrs.spring23.repository.spec.filter.BookFilterBuilder;
import ru.korolkovrs.spring23.rest.controller.BookController;
import ru.korolkovrs.spring23.rest.dto.converter.BookDtoConverter;
import ru.korolkovrs.spring23.service.BookService;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({BookController.class})
public class EndpointsTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private BookDtoConverter bookDtoConverter;

    @MockBean
    private BookFilterBuilder bookFilterBuilder;

    @Autowired
    private ObjectMapper mapper;

    @ParameterizedTest
    @MethodSource("provideUrlsRequiringAuthentication")
    @DisplayName("Должен перенаправлять неавторизованного пользователя на страницу аутентификации")
    void authenticationRequiringUrlsProtected(MockHttpServletRequestBuilder requestBuilder) throws Exception {
        mockMvc.perform(requestBuilder)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @ParameterizedTest
    @MethodSource("provideUrlsRequiringAdminAuthorization")
    @DisplayName("Не должен пропускать на URL пользователя с ролью, отличной от ADMIN")
    void authorizationAdminRequiringUrlsProtected(MockHttpServletRequestBuilder requestBuilder) throws Exception {
        mockMvc.perform(requestBuilder)
                .andExpect(status().isForbidden());
    }

    static Stream<MockHttpServletRequestBuilder> provideUrlsRequiringAuthentication() {
        return Stream.of(
                get("/api/v1/authors"),
                get("/api/v1/genres"),
                get("/api/v1/books"),
                get("/api/v1/books/*"),
                post("/api/v1/books"),
                put("/api/v1/books"),
                delete("/api/v1/books/*"),
                post("/api/v1/comments"),
                put("/api/v1/comments"),
                delete("/api/v1/comments/*")
        );
    }

    static Stream<MockHttpServletRequestBuilder> provideUrlsRequiringAdminAuthorization() throws JsonProcessingException {
        return Stream.of(
                post("/api/v1/books"),
                put("/api/v1/books"),
                delete("/api/v1/books/*"),
                put("/api/v1/comments"),
                delete("/api/v1/comments/*")
        );
    }
}
