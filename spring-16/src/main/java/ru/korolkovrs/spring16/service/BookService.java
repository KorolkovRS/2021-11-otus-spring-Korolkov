package ru.korolkovrs.spring16.service;

import ru.korolkovrs.spring16.domain.Author;
import ru.korolkovrs.spring16.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    void save(Book book);

    Optional<Book> findById(Long id);

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(Author author);

    List<Book> findAll();

    void deleteById(Long id);
}
