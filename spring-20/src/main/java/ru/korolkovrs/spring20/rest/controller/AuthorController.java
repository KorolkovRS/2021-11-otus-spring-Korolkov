package ru.korolkovrs.spring20.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.korolkovrs.spring20.repository.AuthorRepository;
import ru.korolkovrs.spring20.rest.dto.AuthorDto;
import ru.korolkovrs.spring20.rest.dto.converter.AuthorDtoConverter;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorRepository authorRepository;
    private final AuthorDtoConverter authorDtoConverter;

    @GetMapping("/api/v1/authors")
    public Flux<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().map(authorDtoConverter::toDto);
    }
}
