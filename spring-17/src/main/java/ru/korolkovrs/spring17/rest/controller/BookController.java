package ru.korolkovrs.spring17.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.korolkovrs.spring17.domain.Book;
import ru.korolkovrs.spring17.exception.NotFoundException;
import ru.korolkovrs.spring17.rest.dto.RequestBookDto;
import ru.korolkovrs.spring17.rest.dto.ResponseBookDto;
import ru.korolkovrs.spring17.rest.dto.converter.BookDtoConverter;
import ru.korolkovrs.spring17.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookDtoConverter bookDtoConverter;

    @GetMapping("/api/v1/books")
    public List<ResponseBookDto> getAllBook() {
        List<ResponseBookDto> dtos = bookService.findAll().stream().map(bookDtoConverter::toResponseDto).collect(Collectors.toList());
        return dtos;
    }

    @GetMapping("/api/v1/books/{id}")
    public ResponseBookDto getBook(@PathVariable Long id) {
        return bookDtoConverter.toResponseDto(bookService.findById(id).orElseThrow(NotFoundException::new));
    }

    @PostMapping("/api/v1/books")
    public ResponseBookDto saveBook(@RequestBody RequestBookDto requestBookDto) {
        Book book = bookDtoConverter.toDomainObject(requestBookDto);
        return bookDtoConverter.toResponseDto(bookService.save(book));
    }

    @PutMapping("api/v1/books")
    public ResponseBookDto updateBook(@RequestBody RequestBookDto requestBookDto) {
        Book book = bookDtoConverter.toDomainObject(requestBookDto);
        return bookDtoConverter.toResponseDto(bookService.save(book));
    }

    @DeleteMapping("/api/v1/books/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
