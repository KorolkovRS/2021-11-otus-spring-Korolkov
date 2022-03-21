package ru.korolkovrs.spring23.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.korolkovrs.spring23.rest.dto.AuthorDto;
import ru.korolkovrs.spring23.rest.dto.converter.AuthorDtoConverter;
import ru.korolkovrs.spring23.service.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorDtoConverter authorDtoConverter;

    @GetMapping("/api/v1/authors")
    public List<AuthorDto> getAllAuthors() {
        return authorService.findAll().stream()
                .map(authorDtoConverter::toDto)
                .collect(Collectors.toList());
    }
}
