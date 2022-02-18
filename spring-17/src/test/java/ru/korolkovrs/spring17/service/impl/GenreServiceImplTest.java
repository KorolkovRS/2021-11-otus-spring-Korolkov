package ru.korolkovrs.spring17.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.korolkovrs.spring17.domain.Genre;
import ru.korolkovrs.spring17.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GenreServiceImplTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreServiceImpl genreService;

    @Test
    void shouldFindGenreById() {
        Genre expectedGenre = new Genre(1L, "Genre");
        given(genreRepository.findById(1L)).willReturn(Optional.of(expectedGenre));
        Genre genre = genreService.findById(1L).get();

        assertThat(genre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @Test
    void shouldFindAllGenres() {
        List<Genre> expectedGenres = List.of(new Genre(1L, "Genre"));
        given(genreRepository.findAll()).willReturn(expectedGenres);
        List<Genre> authors = genreService.findAll();

        assertThat(authors).usingRecursiveComparison().isEqualTo(expectedGenres);
    }

    @Test
    void shouldSaveGenre() {
        Genre genre = new Genre(1L, "Genre");
        genreService.save(genre);

        verify(genreRepository, times(1)).save(genre);
    }
}