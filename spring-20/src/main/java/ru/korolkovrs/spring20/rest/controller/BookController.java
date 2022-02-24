package ru.korolkovrs.spring20.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.korolkovrs.spring20.domain.Book;
import ru.korolkovrs.spring20.exception.IllegalResponseDataException;
import ru.korolkovrs.spring20.repository.AuthorRepository;
import ru.korolkovrs.spring20.repository.BookRepository;
import ru.korolkovrs.spring20.repository.CommentRepository;
import ru.korolkovrs.spring20.repository.GenreRepository;
import ru.korolkovrs.spring20.rest.dto.RequestBookDto;
import ru.korolkovrs.spring20.rest.dto.ResponseBookDto;
import ru.korolkovrs.spring20.rest.dto.converter.BookDtoConverter;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;
    private final BookDtoConverter bookDtoConverter;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;

    @GetMapping("/api/v1/books")
    public Flux<ResponseBookDto> getAllBook() {
        return bookRepository.findAll().map(bookDtoConverter::toResponseDto);
    }

    @GetMapping("/api/v1/books/{id}")
    public Mono<ResponseBookDto> getBook(@PathVariable String id) {
        return bookRepository.findById(id).map(bookDtoConverter::toResponseDto);
    }

    @PostMapping("/api/v1/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseBookDto> saveBook(@Valid @RequestBody RequestBookDto requestBookDto) {
        if (requestBookDto.getId() != null) {
            throw new IllegalResponseDataException("Attempt to save an existing book");
        }
        return collectBookAndSave(requestBookDto);
    }

    @PutMapping("/api/v1/books")
    public Mono<ResponseBookDto> updateBook(@Valid @RequestBody RequestBookDto requestBookDto) {
        if (requestBookDto.getId() == null) {
            throw new IllegalResponseDataException("The id of the updated book is not specified");
        }
        return collectBookAndSave(requestBookDto);
    }

    @DeleteMapping("/api/v1/books/{id}")
    public void deleteBookById(@PathVariable String id) {
        Mono.zip(
                bookRepository.deleteById(id),
                commentRepository.deleteAllByBookId(id)
        ).subscribe();
    }

    private Mono<ResponseBookDto> collectBookAndSave(RequestBookDto requestBookDto) {
        return Mono.zip(
                authorRepository.findById(requestBookDto.getAuthorId()),
                genreRepository.findById(requestBookDto.getGenreId()),
                (a, g) -> new Book(requestBookDto.getId(), requestBookDto.getTitle(), a, g))
                .flatMap(b -> bookRepository.save(b))
                .map(b -> bookDtoConverter.toResponseDto(b));
    }
}
