package ru.korolkovrs.spring17.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.korolkovrs.spring17.domain.Book;
import ru.korolkovrs.spring17.exception.NotFoundException;
import ru.korolkovrs.spring17.rest.dto.BookDto;
import ru.korolkovrs.spring17.rest.dto.converter.BookDtoConverter;
import ru.korolkovrs.spring17.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookDtoConverter bookDtoConverter;

    @GetMapping("/api/v1/books")
    public List<Book> getAllBook() {
        return bookService.findAll();
    }

    @GetMapping("/api/v1/books/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/api/v1/books")
    public Book saveBook(@RequestBody BookDto bookDto) {
        Book book = bookDtoConverter.toDomainObject(bookDto);
        return bookService.save(book);
    }

    @PutMapping("api/v1/books")
    public Book updateBook(@RequestBody BookDto bookDto) {
        Book book = bookDtoConverter.toDomainObject(bookDto);
        return bookService.save(book);
    }

    @DeleteMapping("/api/v1/books/{id}")
    public void deleteBookById(@PathVariable Long id) {

    }
}
