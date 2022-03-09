package ru.korolkovrs.spring20.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.korolkovrs.spring20.domain.Author;
import ru.korolkovrs.spring20.domain.Book;
import ru.korolkovrs.spring20.domain.Comment;
import ru.korolkovrs.spring20.domain.Genre;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void shouldCorrectFindByBookId() {
        Book expectedBook = new Book("book", new Author(), new Genre());
        Mono<Comment> commentMono = commentRepository.save(new Comment("test", expectedBook));

        StepVerifier
                .create(commentMono)
                .assertNext(comment -> assertNotNull(comment.getId()))
                .expectComplete()
                .verify();
    }

    @Test
    void shouldCorrectRemoveByBookId() {
        String bookId = "book_id";
        Book expectedBook = new Book(bookId, "book", new Author(), new Genre());
        commentRepository.save(new Comment("1", "test1", expectedBook)).subscribe();
        commentRepository.save(new Comment("2", "test2", new Book(bookId, "book11", new Author(), new Genre()))).block();

        commentRepository.deleteAllByBookId(bookId).block();

        Flux<Comment> commentFlux = commentRepository.findByBookId(bookId);

        StepVerifier
                .create(commentFlux)
                .expectNextCount(0)
                .expectComplete()
                .verify();
    }
}
