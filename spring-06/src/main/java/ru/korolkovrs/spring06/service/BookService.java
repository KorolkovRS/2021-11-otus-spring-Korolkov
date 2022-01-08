package ru.korolkovrs.spring06.service;

import org.springframework.transaction.annotation.Transactional;
import ru.korolkovrs.spring06.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    void save(Book book);

    Optional<Book> findById(Long id);

    List<Book> findByTitle(String title);

    @Transactional(readOnly = true)
    List<Book> findByAuthorName(String authorName);

    List<Book> findAll();

    void deleteById(Long id);
}
