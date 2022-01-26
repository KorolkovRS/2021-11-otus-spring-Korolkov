package ru.korolkovrs.spring16.service;

import ru.korolkovrs.spring16.domain.Book;
import ru.korolkovrs.spring16.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> findById(Long id);
    List<Comment> findAll();
    Comment save(Comment comment);
    void deleteById(Long id);
    List<Comment> findByBook(Book book);
}
