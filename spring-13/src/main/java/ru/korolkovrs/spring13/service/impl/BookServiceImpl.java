package ru.korolkovrs.spring13.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korolkovrs.spring13.domain.Genre;
import ru.korolkovrs.spring13.repository.BookRepository;
import ru.korolkovrs.spring13.domain.Author;
import ru.korolkovrs.spring13.domain.Book;
import ru.korolkovrs.spring13.service.BookService;
import ru.korolkovrs.spring13.service.CommentService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CommentService commentService;

    @Override
    @Transactional
    public Book save(Book book) {
        if (book.getId() == null) {
            return bookRepository.save(book);
        }
        bookRepository.save(book);
        commentService.updateBook(book);
        return book;
    }

    @Override
    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findAllByTitleIgnoreCaseContaining(title);
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        return bookRepository.findAllByAuthorId(author.getId());
    }

    @Override
    public List<Book> findByGenre(Genre genre) {
        return bookRepository.findAllByGenreId(genre.getId());
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Книги с id=%s не найдено", id))
        );
        bookRepository.delete(book);
        commentService.deleteByBook(book);
    }

    @Override
    public void updateBookAuthor(Author author) {
        bookRepository.updateAuthor(author);
    }

    @Override
    public void updateBookGenre(Genre genre) {
        bookRepository.updateGenre(genre);
    }
}
