package ru.korolkovrs.spring.dao;

import ru.korolkovrs.spring.domain.Genre;

import java.util.List;

public interface GenreDao {
    Genre getById(Integer id);

    List<Genre> getAll();

    void save(Genre genre);
}
