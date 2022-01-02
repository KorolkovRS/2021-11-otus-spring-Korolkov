package ru.korolkovrs.spring06.dao;

import ru.korolkovrs.spring06.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Optional<Genre> findById(Long id);

    List<Genre> findAll();

    Genre save(Genre genre);
}
