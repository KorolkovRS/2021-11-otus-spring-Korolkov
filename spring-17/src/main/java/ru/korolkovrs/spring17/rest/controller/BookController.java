package ru.korolkovrs.spring17.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.korolkovrs.spring17.domain.Book;
import ru.korolkovrs.spring17.exception.IllegalResponseDataException;
import ru.korolkovrs.spring17.exception.ResourceNotFoundException;
import ru.korolkovrs.spring17.repository.spec.BookSpecification;
import ru.korolkovrs.spring17.repository.spec.filter.BookFilter;
import ru.korolkovrs.spring17.repository.spec.filter.BookFilterBuilder;
import ru.korolkovrs.spring17.rest.dto.RequestBookDto;
import ru.korolkovrs.spring17.rest.dto.ResponseBookDto;
import ru.korolkovrs.spring17.rest.dto.converter.BookDtoConverter;
import ru.korolkovrs.spring17.service.BookService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookDtoConverter bookDtoConverter;
    private final BookFilterBuilder bookFilterBuilder;

    @GetMapping("/api/v1/books")
    public List<ResponseBookDto> getAllBook(@RequestParam Map<String, String> filterParams) {
        BookFilter bookFilter = bookFilterBuilder.buildBookFilter(filterParams);
        Specification<Book> specification = BookSpecification.build(bookFilter);
        List<Book> books = bookService.findAll(specification);
        List<ResponseBookDto> dtos = books
                .stream()
                .map(bookDtoConverter::toResponseDtoWithoutComments)
                .collect(Collectors.toList());
        return dtos;
    }

    @GetMapping("/api/v1/books/{id}")
    public ResponseBookDto getBook(@PathVariable Long id) {
        return bookDtoConverter.toResponseDtoIncludeComments(bookService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Book with id=%d not found", id))));
    }

    @PostMapping("/api/v1/books")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBookDto saveBook(@Valid @RequestBody RequestBookDto requestBookDto) {
        if (requestBookDto.getId() != null) {
            throw new IllegalResponseDataException("Attempt to save an existing book");
        }
        Book book = bookDtoConverter.toDomainObject(requestBookDto);
        book = bookService.save(book);
        return bookDtoConverter.toResponseDtoIncludeComments(book);
    }

    @PutMapping("/api/v1/books")
    public ResponseBookDto updateBook(@Valid @RequestBody RequestBookDto requestBookDto) {
        if (requestBookDto.getId() == null) {
            throw new IllegalResponseDataException("The id of the updated book is not specified");
        }
        Book book = bookDtoConverter.toDomainObject(requestBookDto);
        book = bookService.save(book);
        return bookDtoConverter.toResponseDtoIncludeComments(book);
    }

    @DeleteMapping("/api/v1/books/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
