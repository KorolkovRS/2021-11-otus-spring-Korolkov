package ru.korolkovrs.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.korolkovrs.spring.dao.BookDaoJdbc;
import ru.korolkovrs.spring.domain.Author;
import ru.korolkovrs.spring.domain.Book;
import ru.korolkovrs.spring.domain.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Book service test")
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookDaoJdbc dao;

    private static final Author EXIST_AUTHOR_1 = new Author(1L, "Герберт Уэллс");
    private static final Genre EXIST_GENRE_1 = new Genre(1L, "Фантастика");
    private static final Author EXIST_AUTHOR_2 = new Author(2L, "Гарри Гаррисон");

    @Test
    @DisplayName("Должен делегировать запрос на сохранение книги дао")
    void shouldSaveBook() {
        Book book1 = new Book(1L, "Война Миров", EXIST_AUTHOR_1, EXIST_GENRE_1);
        Book book2 = new Book();
        book2.setTitle("Машина времени");
        book2.setAuthor(EXIST_AUTHOR_1);
        book2.setGenre(EXIST_GENRE_1);
        bookService.save(book1);
        bookService.save(book2);

        assertAll(
                () -> verify(dao, times(1)).save(book1),
                () -> verify(dao, times(1)).save(book2)
        );
    }

    @Test
    @DisplayName("Должен вернуть книгу по id")
    void shouldGetBookById() {
        Book book1 = new Book(1L, "Война Миров", EXIST_AUTHOR_1, EXIST_GENRE_1);
        Book book2 = new Book(2L, "Неукратимая планета", EXIST_AUTHOR_2, EXIST_GENRE_1);
        given(dao.getById(1L)).willReturn(book1);
        given(dao.getById(2L)).willReturn(book2);
        assertAll(
                () -> assertThat(bookService.getById(1L)).usingRecursiveComparison().isEqualTo(book1),
                () -> assertThat(bookService.getById(2L)).usingRecursiveComparison().isEqualTo(book2)
        );
    }

    @Test
    @DisplayName("Должен вернуть все книги")
    void shouldGetAllBook() {
        List<Book> books = List.of(
                new Book(1L, "Война Миров", EXIST_AUTHOR_1, EXIST_GENRE_1),
                new Book(2L, "Неукратимая планета", EXIST_AUTHOR_2, EXIST_GENRE_1)
        );
        given(dao.getAll()).willReturn(books);
        assertThat(dao.getAll()).usingRecursiveComparison().isEqualTo(books);
    }

    @Test
    @DisplayName("Должен делегировать запрос на изменение книги дао")
    void shouldUpdateBook() {
        Book book = new Book(1L, "Война Миров", EXIST_AUTHOR_1, EXIST_GENRE_1);
        bookService.update(book);
        verify(dao, times(1)).update(book);
    }

    @Test
    @DisplayName("Должен делегировать запрос на удаление книги дао")
    void shouldDeleteBookById() {
        bookService.deleteById(32L);
        verify(dao, times(1)).deleteById(32L);
    }
}