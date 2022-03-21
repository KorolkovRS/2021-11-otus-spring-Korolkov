package ru.korolkovrs.spring23.service;

import ru.korolkovrs.spring23.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Optional<Genre> findById(Long id);

    List<Genre> findAll();

    Genre save(Genre genre);
}
