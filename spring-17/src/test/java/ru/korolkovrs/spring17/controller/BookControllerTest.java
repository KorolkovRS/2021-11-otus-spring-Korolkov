package ru.korolkovrs.spring17.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.korolkovrs.spring17.domain.Author;
import ru.korolkovrs.spring17.domain.Book;
import ru.korolkovrs.spring17.domain.Genre;
import ru.korolkovrs.spring17.rest.dto.RequestBookDto;
import ru.korolkovrs.spring17.rest.dto.converter.BookDtoConverter;
import ru.korolkovrs.spring17.service.AuthorService;
import ru.korolkovrs.spring17.service.BookService;
import ru.korolkovrs.spring17.service.GenreService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest()
class BookControllerTest {
    private Book book1;
    private Book book2;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private BookDtoConverter bookDtoConverter;

    @PostConstruct
    public void init() {
        book1 = new Book(1L, "book", new Author(1L, null), new Genre(1L, null), null);
        book2 = new Book(2L, "book", new Author(2L, null), new Genre(2L, null), null);
    }

    @Test
    @DisplayName("Должен возвращать все книги")
    public void shouldReturnCorrectBookList() throws Exception {
        List<Book> expectedBooks = List.of(book1, book2);
        given(bookService.findAll()).willReturn(expectedBooks);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", expectedBooks));
    }

    @Test
    @DisplayName("Должен возвращать книгу по id")
    public void shouldReturnCorrectBookById() throws Exception {
        given(bookService.findById(book1.getId())).willReturn(Optional.of(book1));

        mockMvc.perform(get("/book")
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("book"))
                .andExpect(model().size(2))
                .andExpect(model().attributeExists("book", "comment"))
                .andExpect(model().attribute("book", book1));
    }

    @Test
    @DisplayName("Должен возвращать форму редактирования книги")
    public void shouldReturnBookEditForm() throws Exception {
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();

        given(bookService.findById(book1.getId())).willReturn(Optional.of(book1));
        given(authorService.findAll()).willReturn(authors);
        given(genreService.findAll()).willReturn(genres);

        mockMvc.perform(get("/book/edit")
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit_book"))
                .andExpect(model().size(3))
                .andExpect(model().attributeExists("book", "authors", "genres"));
    }

    @Test
    @DisplayName("Должен корректно обновлять книгу")
    public void shouldCorrectUpdateBook() throws Exception {
        RequestBookDto requestBookDto = new RequestBookDto(
                book1.getId(),
                book1.getTitle(),
                book1.getAuthor().getId(),
                book1.getGenre().getId()
        );
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();

        given(authorService.findAll()).willReturn(authors);
        given(genreService.findAll()).willReturn(genres);
        given(bookDtoConverter.toDomainObject(requestBookDto)).willReturn(book1);

        mockMvc.perform(post("/book/edit")
                .param("id", String.valueOf(requestBookDto.getId()))
                .param("title", requestBookDto.getTitle())
                .param("author", String.valueOf(requestBookDto.getAuthor()))
                .param("genre", String.valueOf(requestBookDto.getGenre())))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/"));

        verify(bookService, times(1)).save(book1);
    }

    @Test
    @DisplayName("Должен удалять книгу по id")
    public void shouldCorrectRemoveBookById() throws Exception {
        mockMvc.perform(delete("/book")
                .param("id", "1"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/"));

        verify(bookService, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Должне возвращать форму добавления книги")
    public void shouldReturnBookAddForm() throws Exception {
        List<Author> authors = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();

        given(authorService.findAll()).willReturn(authors);
        given(genreService.findAll()).willReturn(genres);

        mockMvc.perform(get("/book/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add_book"))
                .andExpect(model().size(3))
                .andExpect(model().attributeExists("book", "authors", "genres"));
    }
}
