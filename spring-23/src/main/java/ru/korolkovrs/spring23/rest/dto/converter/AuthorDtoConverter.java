package ru.korolkovrs.spring23.rest.dto.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring23.domain.Author;
import ru.korolkovrs.spring23.rest.dto.AuthorDto;

@Component
@RequiredArgsConstructor
public class AuthorDtoConverter {
    public Author toDomainObject(AuthorDto dto) {
        Author author = new Author(
                dto.getId(),
                dto.getName()
        );
        return author;
    }

    public AuthorDto toDto(Author author) {
        AuthorDto dto = new AuthorDto(
                author.getId(),
                author.getName()
        );
        return dto;
    }
}
