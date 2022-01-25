package ru.korolkovrs.spring13.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import ru.korolkovrs.spring13.domain.Author;
import ru.korolkovrs.spring13.domain.Book;
import ru.korolkovrs.spring13.domain.Comment;
import ru.korolkovrs.spring13.domain.Genre;
import ru.korolkovrs.spring13.repository.BookRepository;
import ru.korolkovrs.spring13.repository.CommentRepository;
import ru.korolkovrs.spring13.service.impl.AuthorServiceImpl;
import ru.korolkovrs.spring13.service.impl.BookServiceImpl;
import ru.korolkovrs.spring13.service.impl.CommentServiceImpl;
import ru.korolkovrs.spring13.util.CommentDataResolver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Book service integration test")
@DataMongoTest
@Import({BookServiceImpl.class, CommentServiceImpl.class, CommentDataResolver.class})
public class BookServiceImplIntegrationTest {
    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;

    @AfterEach
    void cleanUp() {
        bookRepository.deleteAll();
        commentRepository.deleteAll();
    }

    @Test
    @DisplayName("Должен сохранять комментарий")
    void shouldSaveAuthorCorrect() {
        Comment existingComment = commentService.save(new Comment("Комментарий 1", new Book()));
        Comment comment = commentService.findById(existingComment.getId()).get();
        assertThat(comment).usingRecursiveComparison().isEqualTo(existingComment);
    }

    @Test
    @DisplayName("При обновлении книги в коллекции должен обновлять книгу в комментариях")
    void shouldUpdateBookInComments() {
        String correctBookTitle = "Солярис";

        Book book1 = bookService.save(new Book("Cjkzhbc", null, null));
        Book book2 = bookService.save(new Book("2", "Война и мир", null, null));

        commentService.save(new Comment("Комментарий 1", book1));
        commentService.save(new Comment("Комментарий 2", book1));
        commentService.save(new Comment("Комментарий 3", book2));

        book1.setTitle(correctBookTitle);
        book1.setAuthor(new Author("1", "Станислав Лем"));
        book1.setGenre(new Genre("1", "Фантастика"));

        bookService.save(book1);
        List<Comment> comments = commentRepository.findAllByBook(book1);

        assertAll(
                () -> assertThat(comments).hasSize(2),
                () -> assertThat(comments.get(0).getBook()).isEqualTo(book1),
                () -> assertThat(comments.get(0).getBook()).isEqualTo(book1)
        );
    }
}
