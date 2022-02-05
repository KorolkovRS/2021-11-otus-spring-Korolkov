package ru.korolkovrs.spring16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.korolkovrs.spring16.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBookId(Long id);
}
