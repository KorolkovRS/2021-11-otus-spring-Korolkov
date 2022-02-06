package ru.korolkovrs.spring17.rest.dto.converter;

import org.junit.jupiter.api.Test;
import ru.korolkovrs.spring17.domain.Genre;
import ru.korolkovrs.spring17.rest.dto.GenreDto;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenreDtoConverterTest {
    private GenreDtoConverter converter = new GenreDtoConverter();

    @Test
    public void shouldCorrectConvertDtoToDomain() {
        GenreDto dto = new GenreDto(1L, "Genre");
        Genre genre = converter.toDomainObject(dto);

        assertAll(
                () -> assertEquals(dto.getId(), genre.getId()),
                () -> assertEquals(dto.getGenreName(), genre.getGenreName())
        );
    }

    @Test
    public void shouldCorrectConvertDomainToDto() {
        Genre genre = new Genre(1L, "Author");
        GenreDto dto = converter.toDto(genre);

        assertAll(
                () -> assertEquals(dto.getId(), genre.getId()),
                () -> assertEquals(dto.getGenreName(), genre.getGenreName())
        );
    }
}