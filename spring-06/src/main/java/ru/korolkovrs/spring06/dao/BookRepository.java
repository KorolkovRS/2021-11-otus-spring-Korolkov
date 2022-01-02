package ru.korolkovrs.spring06.dao;

import ru.korolkovrs.spring06.domain.Book;

import java.util.List;

public interface BookRepository {
    void save(Book book);

    Book getById(Long id);

    List<Book> getAll();

    void update(Book book);

    void deleteById(Long id);
}
