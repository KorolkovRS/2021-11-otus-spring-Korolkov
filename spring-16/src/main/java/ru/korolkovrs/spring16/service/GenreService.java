package ru.korolkovrs.spring16.service;

import ru.korolkovrs.spring16.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Optional<Genre> findById(Long id);

    List<Genre> findAll();

    Genre save(Genre genre);
}
