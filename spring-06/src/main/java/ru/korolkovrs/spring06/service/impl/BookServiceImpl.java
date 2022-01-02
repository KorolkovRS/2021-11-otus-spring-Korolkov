package ru.korolkovrs.spring06.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring06.dao.BookRepository;
import ru.korolkovrs.spring06.domain.Book;
import ru.korolkovrs.spring06.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public void update(Book book) {
        bookRepository.update(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
