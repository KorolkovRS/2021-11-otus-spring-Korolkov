package ru.korolkovrs.spring06.dao;

import ru.korolkovrs.spring06.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> findById(Long id);
    List<Comment> findAll();
    Comment save(Comment comment);
    void delete(Comment comment);
}
