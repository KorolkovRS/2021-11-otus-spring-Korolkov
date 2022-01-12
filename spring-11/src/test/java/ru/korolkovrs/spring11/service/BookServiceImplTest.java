package ru.korolkovrs.spring11.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.korolkovrs.spring11.domain.Author;
import ru.korolkovrs.spring11.domain.Book;
import ru.korolkovrs.spring11.domain.Genre;
import ru.korolkovrs.spring11.service.impl.BookServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//@DisplayName("Book service test")
//@ExtendWith(MockitoExtension.class)
//class BookServiceImplTest {
//    @InjectMocks
//    private BookServiceImpl bookService;
//
//    @Mock
//    private BookRepositoryJpa dao;
//
//    private static final Author EXIST_AUTHOR_1 = new Author(1L, "Герберт Уэллс");
//    private static final Genre EXIST_GENRE_1 = new Genre(1L, "Фантастика");
//    private static final Author EXIST_AUTHOR_2 = new Author(2L, "Гарри Гаррисон");
//
//    @Test
//    @DisplayName("Должен делегировать запрос на сохранение и изменение книги дао")
//    void shouldSaveBook() {
//        Book book1 = new Book(1L, "Неукротимая планета", EXIST_AUTHOR_2, EXIST_GENRE_1, null);
//
//        Book book2 = new Book();
//        book2.setTitle("Война Миров");
//        book2.setAuthor(EXIST_AUTHOR_1);
//        book2.setGenre(EXIST_GENRE_1);
//
//        bookService.save(book1);
//        bookService.save(book2);
//
//        assertAll(
//                () -> verify(dao, times(1)).save(book1),
//                () -> verify(dao, times(1)).save(book2)
//        );
//    }
//
//    @Test
//    @DisplayName("Должен вернуть книгу по id")
//    void shouldGetBookById() {
//        Book book1 = new Book(1L, "Война Миров", EXIST_AUTHOR_1, EXIST_GENRE_1, null);
//        Book book2 = new Book(2L, "Неукратимая планета", EXIST_AUTHOR_2, EXIST_GENRE_1, null);
//
//        given(dao.findById(book1.getId())).willReturn(Optional.of(book1));
//        given(dao.findById(book2.getId())).willReturn(Optional.of(book2));
//        assertAll(
//                () -> assertThat(bookService.findById(book1.getId()).get()).usingRecursiveComparison().isEqualTo(book1),
//                () -> assertThat(bookService.findById(book2.getId()).get()).usingRecursiveComparison().isEqualTo(book2)
//        );
//    }
//
//    @Test
//    @DisplayName("Должен вернуть книгу по названию")
//    void shouldGenBookByTitle() {
//        Book book = new Book(1L, "Война Миров", EXIST_AUTHOR_1, EXIST_GENRE_1, null);
//        List<Book> books = List.of(book);
//
//        given(dao.findByTitle(book.getTitle())).willReturn(books);
//
//        assertThat(bookService.findByTitle(book.getTitle())).usingRecursiveComparison().isEqualTo(books);
//    }
//
//    @Test
//    @DisplayName("Должен вернуть книгу по имени автора")
//    void shouldGenBookByAuthorName() {
//        Book book = new Book(1L, "Война Миров", EXIST_AUTHOR_1, EXIST_GENRE_1, null);
//        List<Book> books = List.of(book);
//
//        given(dao.findByTitle(book.getAuthor().getName())).willReturn(books);
//
//        assertThat(bookService.findByTitle(book.getAuthor().getName())).usingRecursiveComparison().isEqualTo(books);
//    }
//
//    @Test
//    @DisplayName("Должен вернуть все книги")
//    void shouldGetAllBook() {
//        List<Book> books = List.of(
//                new Book(1L, "Война Миров", EXIST_AUTHOR_1, EXIST_GENRE_1, null),
//                new Book(2L, "Неукратимая планета", EXIST_AUTHOR_2, EXIST_GENRE_1, null)
//        );
//        given(dao.findAll()).willReturn(books);
//        assertThat(dao.findAll()).usingRecursiveComparison().isEqualTo(books);
//    }
//
//    @Test
//    @DisplayName("Должен делегировать запрос на удаление книги дао")
//    void shouldDeleteBookById() {
//        Long id = 1L;
//        Book bookWithId1 = new Book();
//        given(dao.findById(id)).willReturn(Optional.of(bookWithId1));
//
//        bookService.deleteById(id);
//
//        assertAll(
//                () -> verify(dao, times(1)).findById(id),
//                () -> verify(dao, times(1)).delete(bookWithId1)
//        );
//    }
//}