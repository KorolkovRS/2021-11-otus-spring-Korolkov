package ru.korolkovrs.spring16.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korolkovrs.spring16.domain.Author;
import ru.korolkovrs.spring16.domain.Book;
import ru.korolkovrs.spring16.exception.NotFoundException;
import ru.korolkovrs.spring16.repository.BookRepository;
import ru.korolkovrs.spring16.service.BookService;

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
        return bookRepository.findAllByTitleIgnoreCaseContaining(title);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findByAuthor(Author author) {
        return bookRepository.findAllByAuthor(author);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAllByOrderById();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Книги с id=%d не найдено", id))
        );
        bookRepository.delete(book);
    }
}
