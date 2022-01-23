package ru.korolkovrs.spring13.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import ru.korolkovrs.spring13.domain.Author;
import ru.korolkovrs.spring13.domain.Book;
import ru.korolkovrs.spring13.repository.AuthorRepository;
import ru.korolkovrs.spring13.repository.BookRepository;
import ru.korolkovrs.spring13.service.impl.AuthorServiceImpl;
import ru.korolkovrs.spring13.service.impl.BookServiceImpl;
import ru.korolkovrs.spring13.service.impl.CommentServiceImpl;
import ru.korolkovrs.spring13.util.CommentDataResolver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Author service integration test")
@DataMongoTest
@Import({AuthorServiceImpl.class, BookServiceImpl.class, CommentServiceImpl.class, CommentDataResolver.class})
public class AuthorServiceImplTest {
    @Autowired
    private AuthorServiceImpl authorService;

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @AfterEach
    void cleanUp() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    @DisplayName("Должен сохранять автора")
    void shouldSaveAuthorCorrect() {
        Author existingAuthor = authorService.save(new Author("Лев Толстой"));
        Author author = authorService.findById(existingAuthor.getId()).get();
        assertThat(author).usingRecursiveComparison().isEqualTo(existingAuthor);
    }

    @Test
    @DisplayName("При обновлении автора в коллекции должен обновлять авторов в книгах")
    void shouldUpdateAuthorInBooks() {
        String correctName = "Роджер Желязны";
        Author author = authorService.save(new Author("Лев Толстой"));
        bookService.save(new Book("Хроники Амбера 1 часть", author, null));
        bookService.save(new Book("Хроники Амбера 2 часть", author, null));

        author.setName(correctName);
        authorService.save(author);

        List<Book> books = bookService.findByAuthor(author);

        assertAll(
                () -> assertThat(books).hasSize(2),
                () -> assertThat(books.get(0).getAuthor().getName()).isEqualTo(correctName),
                () -> assertThat(books.get(0).getAuthor().getName()).isEqualTo(correctName)
        );
    }
}
