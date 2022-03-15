package ru.korolkovrs.spring23.rest.dto.converter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring23.domain.Book;
import ru.korolkovrs.spring23.domain.Comment;
import ru.korolkovrs.spring23.exception.ResourceNotFoundException;
import ru.korolkovrs.spring23.rest.dto.*;
import ru.korolkovrs.spring23.service.AuthorService;
import ru.korolkovrs.spring23.service.CommentService;
import ru.korolkovrs.spring23.service.GenreService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookDtoConverter {
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;
    private final CommentDtoConverter commentDtoConverter;
    private final AuthorDtoConverter authorDtoConverter;
    private final GenreDtoConverter genreDtoConverter;

    public Book toDomainObject(RequestBookDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(authorService.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Author with id=%d not found", dto.getAuthorId())))
        );
        book.setGenre(genreService.findById(dto.getGenreId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Genre with id=%d not found", dto.getGenreId())))
        );
        return book;
    }

    public ResponseBookDto toResponseDtoWithoutComments(Book book) {
        AuthorDto authorDto = authorDtoConverter.toDto(book.getAuthor());
        GenreDto genreDto = genreDtoConverter.toDto(book.getGenre());
        if (book.getComments() == null) {
            book.setComments(new ArrayList<>());
        }
        ResponseBookDto bookDto = new ResponseBookDto(
                book.getId(),
                book.getTitle(),
                authorDto,
                genreDto,
                null
        );
        return bookDto;
    }

    public ResponseBookDto toResponseDtoIncludeComments(Book book) {
        ResponseBookDto bookDto = toResponseDtoWithoutComments(book);
        List<Comment> comments = commentService.findByBook(book);
        List<ResponseCommentDto> commentDtos = comments.stream()
                .map((comment -> commentDtoConverter.toResponseCommentDto(comment)))
                .collect(Collectors.toList());
        bookDto.setComments(commentDtos);
        return bookDto;
    }
}
