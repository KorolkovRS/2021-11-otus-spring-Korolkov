package ru.korolkovrs.spring06.service;

import ru.korolkovrs.spring06.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> findById(Long id);
    List<Comment> findAll();
    Comment save(Comment comment);
    void deleteById(Long id);
}
