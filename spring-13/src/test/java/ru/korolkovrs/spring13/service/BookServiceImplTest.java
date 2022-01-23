package ru.korolkovrs.spring13.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.korolkovrs.spring13.domain.Author;
import ru.korolkovrs.spring13.domain.Book;
import ru.korolkovrs.spring13.domain.Genre;
import ru.korolkovrs.spring13.repository.BookRepository;
import ru.korolkovrs.spring13.service.impl.BookServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Book service test")
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository repository;

    @Mock
    private CommentService commentService;

    private static final Author EXIST_AUTHOR_1 = new Author("Герберт Уэллс");
    private static final Genre EXIST_GENRE_1 = new Genre("Фантастика");
    private static final Author EXIST_AUTHOR_2 = new Author("Гарри Гаррисон");

    @Test
    @DisplayName("Должен делегировать запрос на сохранение и изменение книги дао")
    void shouldSaveBook() {
        Book book1 = new Book("Неукротимая планета", EXIST_AUTHOR_2, EXIST_GENRE_1);

        bookService.save(book1);

        verify(repository, times(1)).save(book1);
    }

    @Test
    @DisplayName("Должен вернуть книгу по id")
    void shouldGetBookById() {
        Book book1 = new Book("1", "Война Миров", EXIST_AUTHOR_1, EXIST_GENRE_1);
        Book book2 = new Book("2", "Неукратимая планета", EXIST_AUTHOR_2, EXIST_GENRE_1);

        given(repository.findById(book1.getId())).willReturn(Optional.of(book1));
        given(repository.findById(book2.getId())).willReturn(Optional.of(book2));

        assertAll(
                () -> assertThat(bookService.findById(book1.getId()).get()).usingRecursiveComparison().isEqualTo(book1),
                () -> assertThat(bookService.findById(book2.getId()).get()).usingRecursiveComparison().isEqualTo(book2)
        );
    }

    @Test
    @DisplayName("Должен вернуть книгу по названию")
    void shouldGetBookByTitle() {
        Book book = new Book("1", "Война Миров", EXIST_AUTHOR_1, EXIST_GENRE_1);
        List<Book> books = List.of(book);

        given(repository.findAllByTitleIgnoreCaseContaining(book.getTitle())).willReturn(books);

        assertThat(bookService.findByTitle(book.getTitle())).usingRecursiveComparison().isEqualTo(books);
    }

    @Test
    @DisplayName("Должен вернуть книгу по автору")
    void shouldGenBookByAuthorName() {
        Book book = new Book("1", "Война Миров", EXIST_AUTHOR_1, EXIST_GENRE_1);
        List<Book> books = List.of(book);

        given(repository.findAllByAuthorId(book.getAuthor().getId())).willReturn(books);

        assertThat(bookService.findByAuthor(book.getAuthor())).usingRecursiveComparison().isEqualTo(books);
    }

    @Test
    @DisplayName("Должен вернуть все книги")
    void shouldGetAllBook() {
        List<Book> books = List.of(
                new Book("1", "Война Миров", EXIST_AUTHOR_1, EXIST_GENRE_1),
                new Book("2", "Неукратимая планета", EXIST_AUTHOR_2, EXIST_GENRE_1)
        );
        given(repository.findAll()).willReturn(books);
        assertThat(repository.findAll()).usingRecursiveComparison().isEqualTo(books);
    }

    @Test
    @DisplayName("Должен делегировать запрос на удаление книги дао")
    void shouldDeleteBookById() {
        String id = "1";
        Book bookWithId1 = new Book();
        given(repository.findById(id)).willReturn(Optional.of(bookWithId1));

        bookService.deleteById(id);

        assertAll(
                () -> verify(repository, times(1)).findById(id),
                () -> verify(repository, times(1)).delete(bookWithId1),
                () -> verify(commentService, times(1)).deleteByBook(bookWithId1)
        );
    }
}