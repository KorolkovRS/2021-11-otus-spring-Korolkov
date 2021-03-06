package ru.korolkovrs.spring.service;

import ru.korolkovrs.spring.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre getById(Long id);

    List<Genre> getAll();

    void save(Genre genre);
}
