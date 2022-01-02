package ru.korolkovrs.spring06.service;

import ru.korolkovrs.spring06.domain.Book;

import java.util.List;

public interface BookService {
    void save(Book book);

    Book getById(Long id);

    List<Book> getAll();

    void update(Book book);

    void deleteById(Long id);
}
