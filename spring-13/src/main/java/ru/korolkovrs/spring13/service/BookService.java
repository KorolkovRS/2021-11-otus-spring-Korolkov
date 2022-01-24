package ru.korolkovrs.spring13.service;

import ru.korolkovrs.spring13.domain.Author;
import ru.korolkovrs.spring13.domain.Book;
import ru.korolkovrs.spring13.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book save(Book book);

    Optional<Book> findById(String id);

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(Author author);

    List<Book> findByGenre(Genre genre);

    List<Book> findAll();

    void deleteById(String id);

    void updateBookAuthor(Author author);

    void updateBookGenre(Genre genre);
}
