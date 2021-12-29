package ru.korolkovrs.spring.service;

import ru.korolkovrs.spring.domain.Author;

import java.util.List;

public interface AuthorService {
    Author getById(Long id);

    Author getByName(String name);

    List<Author> getAll();

    void save(Author author);
}
