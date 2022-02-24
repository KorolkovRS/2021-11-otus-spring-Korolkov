package ru.korolkovrs.spring20.rest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import ru.korolkovrs.spring20.domain.Genre;
import ru.korolkovrs.spring20.repository.GenreRepository;
import ru.korolkovrs.spring20.rest.dto.GenreDto;
import ru.korolkovrs.spring20.rest.dto.converter.GenreDtoConverter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@WebFluxTest({GenreController.class, GenreDtoConverter.class})
@AutoConfigureDataMongo
class GenreControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private GenreRepository genreRepository;

    @Test
    void shouldCorrectGetAllGenres() {
        given(genreRepository.findAll()).willReturn(Flux.just(new Genre("1", "test1"), new Genre("2", "test2")));

        webTestClient.get()
                .uri("/api/v1/genres")
                .exchange()
                .expectBodyList(GenreDto.class)
                .value(result -> assertAll(
                        () -> assertThat(result.size()).isEqualTo(2),
                        () -> assertThat(result.get(0).getId()).isEqualTo("1"),
                        () -> assertThat(result.get(1).getId()).isEqualTo("2")
                ));
    }
}