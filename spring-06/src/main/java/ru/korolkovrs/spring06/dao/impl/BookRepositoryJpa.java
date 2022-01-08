package ru.korolkovrs.spring06.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.korolkovrs.spring06.dao.BookRepository;
import ru.korolkovrs.spring06.domain.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            entityManager.persist(book);
            return book;
        }
        return entityManager.merge(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        EntityGraph<?> entityGraph = entityManager.createEntityGraph("book-comments-entity-graph");
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b " +
                        "JOIN FETCH b.author " +
                        "JOIN FETCH b.genre " +
                        "WHERE b.id = :id",
                Book.class
        );
        query.setParameter("id", id);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<Book> findByTitle(String title) {
        TypedQuery<Book> query = entityManager.createQuery(
                "SELECT b FROM Book b " +
                        "JOIN FETCH b.author " +
                        "JOIN FETCH b.genre " +
                        "WHERE LOWER(b.title) LIKE LOWER(:title)",
                Book.class
        );
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }

    @Override
    public List<Book> findByAuthorName(String authorName) {
        TypedQuery<Book> query = entityManager.createQuery(
                "SELECT b FROM Book b " +
                        "JOIN FETCH b.author " +
                        "JOIN FETCH b.genre " +
                        "WHERE LOWER(b.author.name) LIKE LOWER(:authorName) " +
                        "ORDER BY b.id",
                Book.class
        );
        query.setParameter("authorName", "%" + authorName + "%");
        return query.getResultList();
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery(
                "SELECT b FROM Book b " +
                        "JOIN FETCH b.author " +
                        "JOIN FETCH b.genre " +
                        "ORDER BY b.id",
                Book.class
        );
        return query.getResultList();
    }

    @Override
    public void delete(Book book) {
        entityManager.remove(book);
    }
}
