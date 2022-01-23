package ru.korolkovrs.spring13.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korolkovrs.spring13.domain.Book;
import ru.korolkovrs.spring13.repository.AuthorRepository;
import ru.korolkovrs.spring13.domain.Author;
import ru.korolkovrs.spring13.service.AuthorService;
import ru.korolkovrs.spring13.service.BookService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookService bookService;

    @Override
    public Optional<Author> findById(String id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> findByName(String authorName) {
        return authorRepository.findAllByNameIgnoreCaseContaining(authorName);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    public Author save(Author author) {
        if (author.getId() == null) {
            return authorRepository.save(author);
        }
        List<Book> books = bookService.findByAuthor(author);
        authorRepository.save(author);
        books.forEach(book -> {
            book.setAuthor(author);
            bookService.save(book);
        });
        return author;
    }
}
