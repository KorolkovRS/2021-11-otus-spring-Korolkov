package ru.korolkovrs.spring13.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korolkovrs.spring13.domain.Book;
import ru.korolkovrs.spring13.repository.GenreRepository;
import ru.korolkovrs.spring13.domain.Genre;
import ru.korolkovrs.spring13.service.BookService;
import ru.korolkovrs.spring13.service.GenreService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final BookService bookService;

    @Override
    public Optional<Genre> findById(String id) {
        return genreRepository.findById(id);
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        if (genre.getId() == null) {
            return genreRepository.save(genre);
        }
        List<Book> books = bookService.findByGenre(genre);
        genreRepository.save(genre);
        books.forEach(book -> {
            book.setGenre(genre);
            bookService.save(book);
        });
        return genre;
    }
}
