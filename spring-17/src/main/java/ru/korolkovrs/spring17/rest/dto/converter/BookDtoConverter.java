package ru.korolkovrs.spring17.rest.dto.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring17.domain.Book;
import ru.korolkovrs.spring17.exception.NotFoundException;
import ru.korolkovrs.spring17.rest.dto.BookDto;
import ru.korolkovrs.spring17.service.AuthorService;
import ru.korolkovrs.spring17.service.GenreService;

@Component
@RequiredArgsConstructor
public class BookDtoConverter {
    private final AuthorService authorService;
    private final GenreService genreService;

    public Book toDomainObject(BookDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(authorService.findById(dto.getAuthor()).orElseThrow(NotFoundException::new));
        book.setGenre(genreService.findById(dto.getGenre()).orElseThrow(NotFoundException::new));
        return book;
    }
}
