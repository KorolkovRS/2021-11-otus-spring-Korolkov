package ru.korolkovrs.spring06.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korolkovrs.spring06.dao.BookRepository;
import ru.korolkovrs.spring06.domain.Book;
import ru.korolkovrs.spring06.service.BookService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Книги с id=%d не найдено", id))
        );
        bookRepository.delete(book);
    }
}
