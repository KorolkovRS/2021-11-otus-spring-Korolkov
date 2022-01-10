package ru.korolkovrs.spring06.dao;

import ru.korolkovrs.spring06.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);

    Optional<Book> findById(Long id);

    List<Book> findByTitle(String title);

    List<Book> findByAuthorName(String authorName);

    List<Book> findAll();

    void delete(Book book);
}
