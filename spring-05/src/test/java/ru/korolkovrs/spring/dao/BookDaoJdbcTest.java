package ru.korolkovrs.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.korolkovrs.spring.domain.Author;
import ru.korolkovrs.spring.domain.Book;
import ru.korolkovrs.spring.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Book dao")
@JdbcTest()
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {
    private static final Long EXISTING_BOOK_ID_1 = 1L;
    private static final String EXISTING_TITLE_1 = "Война и мир";
    private static final Author EXISTING_AUTHOR_1 = new Author(1, "Л.Н. Толстой");
    private static final Genre EXISTING_GENRE_1 = new Genre(1, "Русская классика");
    private static final Long EXISTING_BOOK_ID_2 = 2L;
    private static final String EXISTING_TITLE_2 = "Хроники Амбера";
    private static final Author EXISTING_AUTHOR_2 = new Author(2, "Роджер Желязны");
    private static final Genre EXISTING_GENRE_2 = new Genre(2, "Фантастика");

    @Autowired
    private BookDaoJdbc bookDao;

    @Test
    @DisplayName("Должен сохранить новую книгу")
    void shouldSaveBook() {
        Book newBook = new Book();
        newBook.setTitle("Звездный спидвей");
        newBook.setAuthor(EXISTING_AUTHOR_2);
        newBook.setGenre(EXISTING_GENRE_2);
        bookDao.save(newBook);
        Book book = bookDao.getById(3L);
        assertAll(
                () -> assertEquals(book.getTitle(), newBook.getTitle()),
                () -> assertEquals(book.getAuthor(), newBook.getAuthor()),
                () -> assertEquals(book.getGenre(), newBook.getGenre())
        );
    }

    @Test
    @DisplayName("Должен вернуть книгу по id")
    void shouldGetBookById() {
        Book existBook = new Book(EXISTING_BOOK_ID_1, EXISTING_TITLE_1, EXISTING_AUTHOR_1, EXISTING_GENRE_1);
        Book book = bookDao.getById(EXISTING_BOOK_ID_1);
        assertThat(book).usingRecursiveComparison().isEqualTo(existBook);
    }

    @Test
    @DisplayName("Должен вернуть все книги")
    void shouldGetAll() {
        List<Book> existBooks = List.of(new Book(EXISTING_BOOK_ID_1, EXISTING_TITLE_1, EXISTING_AUTHOR_1, EXISTING_GENRE_1),
                new Book(EXISTING_BOOK_ID_2, EXISTING_TITLE_2, EXISTING_AUTHOR_2, EXISTING_GENRE_2));
        List<Book> books = bookDao.getAll();
        assertThat(books).usingRecursiveComparison().isEqualTo(existBooks);
    }

    @Test
    @DisplayName("Должен обновить книгу в БД")
    void shouldBookUpdate() {
        Book updateBook = bookDao.getById(1L);
        updateBook.setAuthor(EXISTING_AUTHOR_2);
        bookDao.update(updateBook);
        assertThat(updateBook).usingRecursiveComparison().isEqualTo(bookDao.getById(1L));
    }

    @Test
    @DisplayName("Должен удалить книгу с указанным id")
    void shouldDeleteBookById() {
        bookDao.deleteById(1L);
        assertAll(
                () -> assertThat(bookDao.getAll()).hasSize(1),
                () -> assertThrows(RuntimeException.class,
                        () -> bookDao.getById(1L))
        );
    }
}