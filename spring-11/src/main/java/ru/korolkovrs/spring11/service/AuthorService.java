package ru.korolkovrs.spring11.service;

import ru.korolkovrs.spring11.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Long id);

    List<Author> findByName(String name);

    List<Author> findAll();

    Author save(Author author);
}
