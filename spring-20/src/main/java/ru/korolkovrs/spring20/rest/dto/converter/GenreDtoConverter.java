package ru.korolkovrs.spring20.rest.dto.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring20.domain.Genre;
import ru.korolkovrs.spring20.rest.dto.GenreDto;

@Component
@RequiredArgsConstructor
public class GenreDtoConverter {
    public Genre toDomainObject(GenreDto dto) {
        Genre genre = new Genre(
                dto.getId(),
                dto.getGenreName()
        );
        return genre;
    }

    public GenreDto toDto(Genre genre) {
        GenreDto dto = new GenreDto(
                genre.getId(),
                genre.getGenreName()
        );
        return dto;
    }
}
