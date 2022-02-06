package ru.korolkovrs.spring17.service;

import org.springframework.data.jpa.domain.Specification;
import ru.korolkovrs.spring17.domain.Author;
import ru.korolkovrs.spring17.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book save(Book book);

    Optional<Book> findById(Long id);

    List<Book> findAll(Specification<Book> spec);

    void deleteById(Long id);
}
