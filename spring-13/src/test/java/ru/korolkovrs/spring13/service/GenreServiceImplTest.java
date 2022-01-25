package ru.korolkovrs.spring13.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import ru.korolkovrs.spring13.domain.Book;
import ru.korolkovrs.spring13.domain.Genre;
import ru.korolkovrs.spring13.repository.BookRepository;
import ru.korolkovrs.spring13.repository.GenreRepository;
import ru.korolkovrs.spring13.service.impl.BookServiceImpl;
import ru.korolkovrs.spring13.service.impl.CommentServiceImpl;
import ru.korolkovrs.spring13.service.impl.GenreServiceImpl;
import ru.korolkovrs.spring13.util.CommentDataResolver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Author service integration test")
@DataMongoTest
@Import({GenreServiceImpl.class, BookServiceImpl.class, CommentServiceImpl.class, CommentDataResolver.class})
public class GenreServiceImplTest {
    @Autowired
    private GenreServiceImpl genreService;

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @AfterEach
    void cleanUp() {
        bookRepository.deleteAll();
        genreRepository.deleteAll();
    }

    @Test
    @DisplayName("Должен сохранять жанр")
    void shouldSaveGenreCorrect() {
        Genre existingGenre = genreService.save(new Genre("Фантастика"));
        Genre genre = genreService.findById(existingGenre.getId()).get();
        assertThat(genre).usingRecursiveComparison().isEqualTo(existingGenre);
    }

    @Test
    @DisplayName("При обновлении жанра в коллекции должен обновлять жанры в книгах")
    void shouldUpdateGenreInBooks() {
        String correctGenreName = "Фантастика";
        Genre genre = genreService.save(new Genre("Исторический"));
        bookService.save(new Book("Хроники Амбера 1 часть", null, genre));
        bookService.save(new Book("Хроники Амбера 2 часть", null, genre));

        genre.setGenreName(correctGenreName);
        genreService.save(genre);

        List<Book> books = bookService.findByGenre(genre);

        assertAll(
                () -> assertThat(books).hasSize(2),
                () -> assertThat(books.get(0).getGenre().getGenreName()).isEqualTo(correctGenreName),
                () -> assertThat(books.get(0).getGenre().getGenreName()).isEqualTo(correctGenreName)
        );
    }
}
