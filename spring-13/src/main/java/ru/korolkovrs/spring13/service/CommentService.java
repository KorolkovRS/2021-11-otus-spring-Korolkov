package ru.korolkovrs.spring13.service;

import ru.korolkovrs.spring13.domain.Book;
import ru.korolkovrs.spring13.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> findById(String id);

    List<Comment> findAll();

    Comment save(Comment comment);

    void deleteById(String id);

    List<Comment> findByBook(Book book);

    void deleteByBook(Book book);

    void updateBook(Book book);
}
