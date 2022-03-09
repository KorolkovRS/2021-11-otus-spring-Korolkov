package ru.korolkovrs.spring20.rest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import ru.korolkovrs.spring20.domain.Author;
import ru.korolkovrs.spring20.repository.AuthorRepository;
import ru.korolkovrs.spring20.rest.dto.AuthorDto;
import ru.korolkovrs.spring20.rest.dto.converter.AuthorDtoConverter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@WebFluxTest({AuthorController.class, AuthorDtoConverter.class})
@AutoConfigureDataMongo
class AuthorControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AuthorRepository authorRepository;

    @Test
    void shouldCorrectGetAllAuthors() {
        given(authorRepository.findAll()).willReturn(Flux.just(new Author("1", "test1"), new Author("2", "test2")));

        webTestClient.get()
                .uri("/api/v1/authors")
                .exchange()
                .expectBodyList(AuthorDto.class)
                .value(result -> assertAll(
                        () -> assertThat(result.size()).isEqualTo(2),
                        () -> assertThat(result.get(0).getId()).isEqualTo("1"),
                        () -> assertThat(result.get(1).getId()).isEqualTo("2")
                ));
    }
}