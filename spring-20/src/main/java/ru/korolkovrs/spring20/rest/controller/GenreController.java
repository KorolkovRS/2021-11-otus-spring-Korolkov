package ru.korolkovrs.spring20.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.korolkovrs.spring20.repository.GenreRepository;
import ru.korolkovrs.spring20.rest.dto.GenreDto;
import ru.korolkovrs.spring20.rest.dto.converter.GenreDtoConverter;

@RestController
@RequiredArgsConstructor
public class GenreController {
    private final GenreRepository genreRepository;
    private final GenreDtoConverter genreDtoConverter;

    @GetMapping("/api/v1/genres")
    public Flux<GenreDto> getAllGenres() {
        return genreRepository.findAll().map(genreDtoConverter::toDto);
    }
}
