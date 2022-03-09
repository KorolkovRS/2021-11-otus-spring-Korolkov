package ru.korolkovrs.spring20.rest.dto.converter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring20.domain.Book;
import ru.korolkovrs.spring20.rest.dto.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookDtoConverter {
    private final AuthorDtoConverter authorDtoConverter;
    private final GenreDtoConverter genreDtoConverter;


    public ResponseBookDto toResponseDto(Book book) {
        AuthorDto authorDto = authorDtoConverter.toDto(book.getAuthor());
        GenreDto genreDto = genreDtoConverter.toDto(book.getGenre());

        ResponseBookDto bookDto = new ResponseBookDto(
                book.getId(),
                book.getTitle(),
                authorDto,
                genreDto
        );
        return bookDto;
    }
}
