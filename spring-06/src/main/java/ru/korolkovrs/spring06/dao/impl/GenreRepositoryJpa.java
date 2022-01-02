package ru.korolkovrs.spring06.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.korolkovrs.spring06.dao.GenreRepository;
import ru.korolkovrs.spring06.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryJpa implements GenreRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Optional<Genre> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = entityManager.createQuery("SELECT g FROM Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() == null) {
            entityManager.persist(genre);
            return genre;
        }
        return entityManager.merge(genre);
    }
}
