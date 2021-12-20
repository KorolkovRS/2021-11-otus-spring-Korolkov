package ru.korolkovrs.spring.dao;

import ru.korolkovrs.spring.domain.Book;

import java.util.List;

public interface BookDao {
    void save(Book book);

    Book getById(Long id);

    List<Book> getAll();

    void update(Book book);

    void deleteById(Long id);
}
