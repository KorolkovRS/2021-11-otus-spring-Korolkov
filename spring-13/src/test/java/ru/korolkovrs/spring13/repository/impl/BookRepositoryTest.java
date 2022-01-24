package ru.korolkovrs.spring13.repository.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.korolkovrs.spring13.domain.Author;
import ru.korolkovrs.spring13.domain.Book;
import ru.korolkovrs.spring13.domain.Genre;
import ru.korolkovrs.spring13.repository.BookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataMongoTest
@DisplayName("Book repository")
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    MongoTemplate template;

    @AfterEach
    void cleanUp() {
        bookRepository.deleteAll();
    }

    @Test
    @DisplayName("Должен найти книгну по Автору")
    void shouldFindBookByAuthorName() {
        Author author1 = new Author("1", "Станислав Лем");
        Author author2 = new Author("2", "Братья Стругацкие");

        template.save(new Book("Солярис", author1, null));
        template.save(new Book("Непобедимый", author1, null));
        template.save(new Book("Пикник на обочине", author2, null));

        assertThat(bookRepository.findAllByAuthorId(author2.getId())).hasSize(1);
    }

    @Test
    @DisplayName("Должен искать книги, содержащие в названии заданную подстроку без учета регистра")
    void shouldFindBooksByTitleIgnoreCase() {
        template.save(new Book("Планета сокровищ", null, null));
        template.save(new Book("Гарри Поттер", null, null));
        template.save(new Book("Незнайка на луне", null, null));

        String pattern = "не";
        List<Book> books = bookRepository.findAllByTitleIgnoreCaseContaining(pattern);
        assertThat(books).hasSize(2);
    }

    @Test
    @DisplayName("Должен обновлять автора книги")
    void shouldUpdateBook() {
        String correctAuthorName = "Станислав Лем";

        Author author1 = new Author("1", "Иван Иванов");
        Author author2 = new Author("2", "Братья Стругацкие");

        template.save(new Book("Солярис", author1, null));
        template.save(new Book("Непобедимый", author1, null));
        template.save(new Book("Пикник на обочине", author2, null));

        author1.setName(correctAuthorName);

        bookRepository.updateAuthor(author1);
        List<Book> books = bookRepository.findAllByAuthorId(author1.getId());

        assertAll(
                () -> assertThat(books).hasSize(2),
                () -> assertThat(books.get(0).getAuthor().getName()).isEqualTo(correctAuthorName),
                () -> assertThat(books.get(1).getAuthor().getName()).isEqualTo(correctAuthorName)
        );
    }

    @Test
    @DisplayName("Должен обновлять жанр книги")
    void shouldUpdateGenreInBooks() {
        String correctGenreName = "Фантастика";

        Genre genre1 = new Genre("1", "Комедия");
        Genre genre2 = new Genre("2", "Классика");

        template.save(new Book("Солярис", null, genre1));
        template.save(new Book("Непобедимый", null, genre1));
        template.save(new Book("Война и мир", null, genre2));

        genre1.setGenreName(correctGenreName);

        bookRepository.updateGenre(genre1);
        List<Book> books = bookRepository.findAllByGenreId(genre1.getId());

        assertAll(
                () -> assertThat(books).hasSize(2),
                () -> assertThat(books.get(0).getGenre().getGenreName()).isEqualTo(correctGenreName),
                () -> assertThat(books.get(0).getGenre().getGenreName()).isEqualTo(correctGenreName)
        );
    }
}