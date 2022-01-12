package ru.korolkovrs.spring11.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.korolkovrs.spring11.domain.Book;
import ru.korolkovrs.spring11.domain.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@DisplayName("Comment dao")
//@DataJpaTest
//@Import(CommentRepositoryJpa.class)
//class CommentRepositoryJpaTest {
//    private final static Long FIRST_COMMENT_ID = 1L;
//    private final static Long EXISTED_BOOK_ID = 2L;
//    private final static Integer EXPECTED_NUMBER_OF_COMMENT = 2;
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private CommentRepositoryJpa commentRepository;
//
//    @Test
//    @DisplayName("Должен вернуть комментарий по id")
//    void shouldFindCommentById() {
//        Comment expectedComment = entityManager.find(Comment.class, FIRST_COMMENT_ID);
//        Comment currentComment = commentRepository.findById(FIRST_COMMENT_ID).get();
//        assertThat(expectedComment).usingRecursiveComparison().isEqualTo(currentComment);
//    }
//
//    @Test
//    @DisplayName("Должен вернуть все комментарии")
//    void shouldFindAllComment() {
//        List<Comment> comments = commentRepository.findAll();
//        assertThat(comments).hasSize(EXPECTED_NUMBER_OF_COMMENT);
//    }
//
//    @Test
//    @DisplayName("Должен сохранить новый комментарий")
//    void shouldSaveComment() {
//        Comment comment = new Comment();
//        comment.setText("Путешествия между мирами");
//        Book book = new Book();
//        book.setId(EXISTED_BOOK_ID);
//        comment.setBook(book);
//        comment = commentRepository.save(comment);
//        Comment currentComment = entityManager.find(Comment.class, comment.getId());
//        assertThat(comment).usingRecursiveComparison().isEqualTo(currentComment);
//    }
//
//    @Test
//    @DisplayName("Должен обновить комментарий")
//    void shouldUpdateAuthor() {
//        Comment comment = entityManager.find(Comment.class, FIRST_COMMENT_ID);
//        entityManager.detach(comment);
//        comment.setText("Путешествия между мирами");
//        Book book = new Book();
//        book.setId(EXISTED_BOOK_ID);
//        comment.setBook(book);
//        comment = commentRepository.save(comment);
//        Comment currentComment = entityManager.find(Comment.class, comment.getId());
//        assertThat(comment).usingRecursiveComparison().isEqualTo(currentComment);
//    }
//
//    @Test
//    @DisplayName("Должен удалить комментарий")
//    void shouldDeleteComment() {
//        Comment comment = entityManager.find(Comment.class, FIRST_COMMENT_ID);
//        commentRepository.delete(comment);
//        Comment currentComment = entityManager.find(Comment.class, FIRST_COMMENT_ID);
//        assertThat(currentComment).isNull();
//    }
//}