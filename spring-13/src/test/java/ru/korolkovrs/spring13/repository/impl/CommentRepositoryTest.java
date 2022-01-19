package ru.korolkovrs.spring13.repository.impl;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.Rollback;
import ru.korolkovrs.spring13.domain.Author;
import ru.korolkovrs.spring13.domain.Book;
import ru.korolkovrs.spring13.domain.Comment;
import ru.korolkovrs.spring13.repository.CommentRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Comment repository")
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoTemplate template;

    private Author existingAuthor = new Author("Братья Стругацкие");

    void cleanUp() {
        commentRepository.deleteAll();
    }

    @Test
    @DisplayName("Должен вернуть все комментарии к книге")
    void shouldFindAllCommentByBook() {
        Book book1 = new Book("Пикник на обочине", null, null);
        Book book2 = new Book("Неукротимая планета", null, null);

        template.save(new Comment("Комментарий 1", book1));
        template.save(new Comment("Комментарий 2", book1));
        template.save(new Comment("Комментарий 3", book2));

        List<Comment> comments = commentRepository.findAllByBook(book1);
        assertThat(comments).hasSize(2);
    }

    @Test
    @DisplayName("Должен удалить комментарии к книге")
    @Rollback
    void shouldDeleteAllByBook() {
        Book book1 = new Book("Пикник на обочине", null, null);
        Book book2 = new Book("Неукротимая планета", null, null);

        template.save(new Comment("Комментарий 1", book1));
        template.save(new Comment("Комментарий 2", book1));
        template.save(new Comment("Комментарий 3", book2));

        commentRepository.deleteAllByBook(book1);
        assertThat(commentRepository.findAllByBook(book1)).hasSize(0);
    }
}