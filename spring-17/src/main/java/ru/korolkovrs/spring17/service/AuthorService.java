package ru.korolkovrs.spring17.service;

import ru.korolkovrs.spring17.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Long id);

    List<Author> findByName(String name);

    List<Author> findAll();

    Author save(Author author);
}
