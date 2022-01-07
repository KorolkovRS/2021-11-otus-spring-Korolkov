package ru.korolkovrs.spring06.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.korolkovrs.spring06.dao.CommentRepository;
import ru.korolkovrs.spring06.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = entityManager.createQuery("SELECT c FROM Comment c", Comment.class);
        return query.getResultList();
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            entityManager.persist(comment);
            return comment;
        }
        return entityManager.merge(comment);
    }

    @Override
    public void delete(Comment comment) {
        entityManager.remove(comment);
    }
}
