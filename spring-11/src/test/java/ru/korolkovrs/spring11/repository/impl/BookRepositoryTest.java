package ru.korolkovrs.spring11.repository.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.korolkovrs.spring11.domain.Book;
import ru.korolkovrs.spring11.repository.BookRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Book repository")
@DataJpaTest
class BookRepositoryTest {
    private final static Long FIRST_BOOK_ID = 1L;
    private final static String BOOK_NAME_PATTERN = "во";
    private final static Integer EXPECTED_NUMBER_OF_BOOK_WITH_NAME_PATTERN = 2;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("Должен найти книгну по Автору")
    void shouldFindBookByAuthorName() {
        Book expectedBook = entityManager.find(Book.class, FIRST_BOOK_ID);
        List<Book> books = bookRepository.findAllByAuthor(expectedBook.getAuthor());

        assertAll(
                () -> assertThat(books).hasSize(1),
                () -> assertThat(books.get(0)).usingRecursiveComparison().isEqualTo(expectedBook)
        );
    }

    @Test
    @DisplayName("Должен искать книги, содержащие в названии заданную подстроку без учета регистра")
    void shouldFindBooksByTitleIgnoreCase() {
        List<Book> books = bookRepository.findAllByTitleIgnoreCaseContaining(BOOK_NAME_PATTERN);
        assertThat(books).hasSize(EXPECTED_NUMBER_OF_BOOK_WITH_NAME_PATTERN);
    }
}