package ru.korolkovrs.spring13.repository.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.korolkovrs.spring13.domain.Author;
import ru.korolkovrs.spring13.domain.Book;
import ru.korolkovrs.spring13.repository.BookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Book repository")
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired MongoTemplate template;

    @AfterEach
    void cleanUp() {
        bookRepository.deleteAll();
    }

    @Test
    @DisplayName("Должен найти книгну по Автору")
    void shouldFindBookByAuthorName() {
        Author author1 = new Author("Станислав Лем");
        Author author2 = new Author("Братья Стругацкие");

        template.save(new Book("Солярис", author1, null));
        template.save(new Book("Непобедимый", author1, null));
        template.save(new Book("Пикник на обочине", author2, null));

        assertThat(bookRepository.findAllByAuthor(author2)).hasSize(1);
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
}