package ru.korolkovrs.spring17.rest.dto.converter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring17.domain.Book;
import ru.korolkovrs.spring17.exception.NotFoundException;
import ru.korolkovrs.spring17.rest.dto.*;
import ru.korolkovrs.spring17.service.AuthorService;
import ru.korolkovrs.spring17.service.GenreService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookDtoConverter {
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentDtoConverter commentDtoConverter;
    private final AuthorDtoConverter authorDtoConverter;
    private final GenreDtoConverter genreDtoConverter;

    public Book toDomainObject(RequestBookDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(authorService.findById(dto.getAuthor()).orElseThrow(NotFoundException::new));
        book.setGenre(genreService.findById(dto.getGenre()).orElseThrow(NotFoundException::new));
        return book;
    }

    public ResponseBookDto toResponseDto(Book book) {
        AuthorDto authorDto = authorDtoConverter.toDto(book.getAuthor());
        GenreDto genreDto = genreDtoConverter.toDto(book.getGenre());
        if (book.getComments() == null) {
            book.setComments(new ArrayList<>());
        }
        List<ResponseCommentDto> commentDtos = book.getComments().stream()
                .map((comment -> commentDtoConverter.toResponseCommentDto(comment)))
                .collect(Collectors.toList());
        ResponseBookDto bookDto = new ResponseBookDto(
                book.getId(),
                book.getTitle(),
                authorDto,
                genreDto,
                commentDtos
        );
        return bookDto;
    }
}
