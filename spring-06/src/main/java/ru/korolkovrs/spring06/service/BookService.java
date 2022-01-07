package ru.korolkovrs.spring06.service;

import ru.korolkovrs.spring06.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    void save(Book book);

    Optional<Book> findById(Long id);

    List<Book> findByTitle(String title);

    List<Book> findAll();

    void deleteById(Long id);
}
