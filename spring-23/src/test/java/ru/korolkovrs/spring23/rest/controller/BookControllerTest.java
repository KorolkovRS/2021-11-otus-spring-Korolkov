package ru.korolkovrs.spring23.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.korolkovrs.spring23.domain.Author;
import ru.korolkovrs.spring23.domain.Book;
import ru.korolkovrs.spring23.domain.Genre;
import ru.korolkovrs.spring23.repository.spec.filter.BookFilter;
import ru.korolkovrs.spring23.repository.spec.filter.BookFilterBuilder;
import ru.korolkovrs.spring23.rest.dto.AuthorDto;
import ru.korolkovrs.spring23.rest.dto.GenreDto;
import ru.korolkovrs.spring23.rest.dto.RequestBookDto;
import ru.korolkovrs.spring23.rest.dto.ResponseBookDto;
import ru.korolkovrs.spring23.rest.dto.converter.BookDtoConverter;
import ru.korolkovrs.spring23.service.BookService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {
    private Book existBook1;
    private Book existBook2;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BookDtoConverter bookDtoConverter;

    @MockBean
    private BookService bookService;

    @MockBean
    private BookFilterBuilder bookFilterBuilder;

    @BeforeEach
    void init() {
        existBook1 = new Book(1L, "book1", new Author(), new Genre(), new ArrayList<>());
        existBook2 = new Book(2L, "book2", new Author(), new Genre(), new ArrayList<>());
    }

    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @Test
    @DisplayName("Должен возвращать все книги")
    void shouldReturnCorrectBookList() throws Exception {
        List<Book> existBooks = List.of(existBook1, existBook2);
        ResponseBookDto expectedDto1 = new ResponseBookDto(1L, "dto1", null, null, null);
        ResponseBookDto expectedDto2 = new ResponseBookDto(2L, "dto2", null, null, null);

        given(bookService.findAll(any())).willReturn(existBooks);
        given(bookFilterBuilder.buildBookFilter(anyMap())).willReturn(new BookFilter());
        given(bookDtoConverter.toResponseDtoWithoutComments(existBook1)).willReturn(expectedDto1);
        given(bookDtoConverter.toResponseDtoWithoutComments(existBook2)).willReturn(expectedDto2);

        List<ResponseBookDto> expectedResponse = existBooks.stream()
                .map(bookDtoConverter::toResponseDtoWithoutComments)
                .collect(Collectors.toList());

        mockMvc.perform(get("/api/v1/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResponse)));
    }

    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @Test
    @DisplayName("Должен возвращать книгу по id")
    void shouldReturnCorrectBookById() throws Exception {
        ResponseBookDto expectedResponse = new ResponseBookDto(1L, "dto1", null, null, new ArrayList<>());

        given(bookService.findById(existBook1.getId())).willReturn(Optional.of(existBook1));
        given(bookDtoConverter.toResponseDtoIncludeComments(existBook1)).willReturn(expectedResponse);

        mockMvc.perform(get("/api/v1/books/" + existBook1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResponse)));
    }


    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    @DisplayName("Должен корректно сохранять книгу")
    void shouldCorrectSaveNewBook() throws Exception {
        RequestBookDto requestBookDto = new RequestBookDto(null, "Book", 1L, 1L);
        Book savingBook = new Book(null, "Book", new Author(), new Genre(), new ArrayList<>());
        ResponseBookDto responseBookDto = new ResponseBookDto(
                1L,
                "book",
                new AuthorDto(1L, "author"),
                new GenreDto(1L, "genre"),
                new ArrayList<>()
        );

        given(bookDtoConverter.toDomainObject(requestBookDto)).willReturn(savingBook);
        given(bookDtoConverter.toResponseDtoIncludeComments(savingBook)).willReturn(responseBookDto);
        given(bookService.save(savingBook)).willReturn(savingBook);

        String expectedResult = mapper.writeValueAsString(bookDtoConverter.toResponseDtoIncludeComments(savingBook));

        mockMvc.perform(post("/api/v1/books").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestBookDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedResult));
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    @DisplayName("Должен корректно обновлять книгу")
    void shouldCorrectUpdateBook() throws Exception {
        RequestBookDto requestBookDto = new RequestBookDto(1L, "Book", 1L, 1L);
        Book savingBook = new Book(null, "Book", new Author(), new Genre(), new ArrayList<>());
        ResponseBookDto responseBookDto = new ResponseBookDto(
                1L,
                "book",
                new AuthorDto(1L, "author"),
                new GenreDto(1L, "genre"),
                new ArrayList<>()
        );

        given(bookDtoConverter.toDomainObject(requestBookDto)).willReturn(savingBook);
        given(bookDtoConverter.toResponseDtoIncludeComments(savingBook)).willReturn(responseBookDto);
        given(bookService.save(savingBook)).willReturn(savingBook);

        String expectedResult = mapper.writeValueAsString(bookDtoConverter.toResponseDtoIncludeComments(savingBook));

        mockMvc.perform(put("/api/v1/books").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestBookDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResult));
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    @DisplayName("Должен удалять книгу по id")
    void shouldCorrectRemoveBookById() throws Exception {
        mockMvc.perform(delete("/api/v1/books/" + existBook1.getId()))
                .andExpect(status().isOk());

        verify(bookService, times(1)).deleteById(existBook1.getId());
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    void shouldNotSaveExistingBook() throws Exception {
        RequestBookDto requestBookDto = new RequestBookDto(1L, "Book", 1L, 1L);

        mockMvc.perform(post("/api/v1/books").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestBookDto)));
        verify(bookService, times(0)).save(any());
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    void shouldValidateSaveBookRequest() throws Exception {
        RequestBookDto requestWithBlankTitle = new RequestBookDto(null, "", 1L, 1L);
        RequestBookDto requestWithEmptyTitle = new RequestBookDto(null, null, 1L, 1L);
        RequestBookDto requestWithEmptyAuthorId = new RequestBookDto(null, "book", null, 1L);
        RequestBookDto requestWithEmptyGenreId = new RequestBookDto(null, "", 1L, null);

        assertAll(
                () -> mockMvc.perform(post("/api/v1/books")
                        .content(mapper.writeValueAsString(requestWithEmptyTitle)))
                        .andExpect(status().is4xxClientError()),
                () -> mockMvc.perform(post("/api/v1/books")
                        .content(mapper.writeValueAsString(requestWithBlankTitle)))
                        .andExpect(status().is4xxClientError()),
                () -> mockMvc.perform(post("/api/v1/books")
                        .content(mapper.writeValueAsString(requestWithEmptyAuthorId)))
                        .andExpect(status().is4xxClientError()),
                () -> mockMvc.perform(post("/api/v1/books")
                        .content(mapper.writeValueAsString(requestWithEmptyGenreId)))
                        .andExpect(status().is4xxClientError())
        );
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    void shouldValidateUpdateBookRequest() throws Exception {
        RequestBookDto requestWithBlankTitle = new RequestBookDto(1L, "", 1L, 1L);
        RequestBookDto requestWithEmptyTitle = new RequestBookDto(1L, null, 1L, 1L);
        RequestBookDto requestWithEmptyAuthorId = new RequestBookDto(1L, "book", null, 1L);
        RequestBookDto requestWithEmptyGenreId = new RequestBookDto(1L, "", 1L, null);

        assertAll(
                () -> mockMvc.perform(put("/api/v1/books")
                        .content(mapper.writeValueAsString(requestWithEmptyTitle)))
                        .andExpect(status().is4xxClientError()),
                () -> mockMvc.perform(put("/api/v1/books")
                        .content(mapper.writeValueAsString(requestWithBlankTitle)))
                        .andExpect(status().is4xxClientError()),
                () -> mockMvc.perform(put("/api/v1/books")
                        .content(mapper.writeValueAsString(requestWithEmptyAuthorId)))
                        .andExpect(status().is4xxClientError()),
                () -> mockMvc.perform(put("/api/v1/books")
                        .content(mapper.writeValueAsString(requestWithEmptyGenreId)))
                        .andExpect(status().is4xxClientError())
        );
    }
}
