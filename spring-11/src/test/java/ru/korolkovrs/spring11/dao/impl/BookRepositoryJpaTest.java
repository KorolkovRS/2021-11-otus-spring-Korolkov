package ru.korolkovrs.spring11.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.korolkovrs.spring11.domain.Author;
import ru.korolkovrs.spring11.domain.Book;
import ru.korolkovrs.spring11.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//@DisplayName("Book dao")
//@DataJpaTest
//@Import(BookRepositoryJpa.class)
//class BookRepositoryJpaTest {
//    private final static Long FIRST_BOOK_ID = 1L;
//    private final static Long EXISTED_AUTHOR_ID = 1L;
//    private final static Long EXISTED_GENRE_ID = 1L;
//    private final static Integer EXPECTED_NUMBER_OF_BOOK = 2;
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private BookRepositoryJpa bookRepository;
//
//    @Test
//    @DisplayName("Должен сохранить книгу")
//    void shouldSaveBook() {
//        Book book = new Book();
//
//        Author author = new Author();
//        author.setId(EXISTED_AUTHOR_ID);
//        Genre genre = new Genre();
//        genre.setId(EXISTED_GENRE_ID);
//
//        book.setTitle("Звездный спидвей");
//        book.setAuthor(author);
//        book.setGenre(genre);
//
//        book = bookRepository.save(book);
//        Book currentComment = entityManager.find(Book.class, book.getId());
//        assertThat(book).usingRecursiveComparison().isEqualTo(currentComment);
//    }
//
//    @Test
//    @DisplayName("Должен обновить книгу")
//    void shouldUpdateBook() {
//        Book book = entityManager.find(Book.class, FIRST_BOOK_ID);
//        entityManager.detach(book);
//
//        Author author = new Author();
//        author.setId(EXISTED_AUTHOR_ID);
//        Genre genre = new Genre();
//        genre.setId(EXISTED_GENRE_ID);
//
//        book.setTitle("Звездный спидвей");
//        book.setAuthor(author);
//        book.setGenre(genre);
//
//        book = bookRepository.save(book);
//        Book currentComment = entityManager.find(Book.class, book.getId());
//        assertThat(book).usingRecursiveComparison().isEqualTo(currentComment);
//    }
//
//    @Test
//    @DisplayName("Должен найти книгну по id")
//    void shouldFindBookById() {
//        Book expectedBook = entityManager.find(Book.class, FIRST_BOOK_ID);
//        Book currentBook = bookRepository.findById(FIRST_BOOK_ID).get();
//        assertThat(expectedBook).usingRecursiveComparison().isEqualTo(currentBook);
//    }
//
//    @Test
//    @DisplayName("Должен найти книгну по названию")
//    void shouldFindBookByTitle() {
//        Book expectedBook = entityManager.find(Book.class, FIRST_BOOK_ID);
//        List<Book> books = bookRepository.findByTitle(expectedBook.getTitle());
//
//        assertAll(
//                () -> assertThat(books).hasSize(1),
//                () -> assertThat(books.get(0)).usingRecursiveComparison().isEqualTo(expectedBook)
//        );
//    }
//
//    @Test
//    @DisplayName("Должен найти книгну по имени автора")
//    void shouldFindBookByAuthorName() {
//        Book expectedBook = entityManager.find(Book.class, FIRST_BOOK_ID);
//        System.out.println(expectedBook.getAuthor().getName());
//        List<Book> books = bookRepository.findByAuthorName(expectedBook.getAuthor().getName());
//
//        assertAll(
//                () -> assertThat(books).hasSize(1),
//                () -> assertThat(books.get(0)).usingRecursiveComparison().isEqualTo(expectedBook)
//        );
//    }
//
//    @Test
//    @DisplayName("Должен найти все книги")
//    void shouldFindAllBook() {
//        List<Book> books = bookRepository.findAll();
//        assertThat(books).hasSize(EXPECTED_NUMBER_OF_BOOK);
//    }
//
//    @Test
//    @DisplayName("Должен удалить книгу")
//    void shouldDeleteBok() {
//        Book book = entityManager.find(Book.class, FIRST_BOOK_ID);
//        bookRepository.delete(book);
//        Book currentBook = entityManager.find(Book.class, FIRST_BOOK_ID);
//        assertThat(currentBook).isNull();
//    }
//}