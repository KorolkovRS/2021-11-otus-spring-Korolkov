package ru.korolkovrs.spring13.service;

import ru.korolkovrs.spring13.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(String id);

    List<Author> findByName(String name);

    List<Author> findAll();

    Author save(Author author);
}
