package ru.korolkovrs.spring13.service;

import ru.korolkovrs.spring13.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Optional<Genre> findById(String id);

    List<Genre> findAll();

    Genre save(Genre genre);
}
