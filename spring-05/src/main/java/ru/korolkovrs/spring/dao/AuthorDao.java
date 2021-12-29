package ru.korolkovrs.spring.dao;

import ru.korolkovrs.spring.domain.Author;

import java.util.List;

public interface AuthorDao {
    Author getById(Long id);

    Author getByName(String name);

    List<Author> getAll();

    void save(Author author);
}
