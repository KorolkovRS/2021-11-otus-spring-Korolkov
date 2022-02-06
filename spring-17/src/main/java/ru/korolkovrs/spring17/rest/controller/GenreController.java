package ru.korolkovrs.spring17.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.korolkovrs.spring17.rest.dto.GenreDto;
import ru.korolkovrs.spring17.rest.dto.converter.GenreDtoConverter;
import ru.korolkovrs.spring17.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;
    private final GenreDtoConverter genreDtoConverter;

    @GetMapping("/api/v1/genres")
    public List<GenreDto> getAllGenres() {
        return genreService.findAll().stream().map(genreDtoConverter::toDto).collect(Collectors.toList());
    }
}
