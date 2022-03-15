package ru.korolkovrs.spring23.rest.dto.converter;

import org.junit.jupiter.api.Test;
import ru.korolkovrs.spring23.domain.Author;
import ru.korolkovrs.spring23.rest.dto.AuthorDto;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorDtoConverterTest {
    private AuthorDtoConverter converter = new AuthorDtoConverter();

    @Test
    public void shouldCorrectConvertDtoToDomain() {
        AuthorDto dto = new AuthorDto(1L, "Author");
        Author author = converter.toDomainObject(dto);

        assertAll(
                () -> assertEquals(dto.getId(), author.getId()),
                () -> assertEquals(dto.getName(), author.getName())
        );
    }

    @Test
    public void shouldCorrectConvertDomainToDto() {
        Author author = new Author(1L, "Author");
        AuthorDto dto = converter.toDto(author);

        assertAll(
                () -> assertEquals(dto.getId(), author.getId()),
                () -> assertEquals(dto.getName(), author.getName())
        );
    }
}
