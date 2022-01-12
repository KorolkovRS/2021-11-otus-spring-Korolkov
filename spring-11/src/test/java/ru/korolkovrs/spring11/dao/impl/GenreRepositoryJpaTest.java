package ru.korolkovrs.spring11.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.korolkovrs.spring11.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@DisplayName("Genre dao")
//@DataJpaTest
//@Import(GenreRepositoryJpa.class)
//class GenreRepositoryJpaTest {
//    private static final Long FIRST_GENRE_ID = 1L;
//    private static final Integer EXPECTED_NUMBER_OF_GENRE = 2;
//
//    @Autowired
//    TestEntityManager entityManager;
//
//    @Autowired
//    GenreRepositoryJpa genreRepository;
//
//    @Test
//    @DisplayName("Должен вернуть жанр по id")
//    void findById() {
//        Genre expectedGenre = entityManager.find(Genre.class, FIRST_GENRE_ID);
//        Genre currentGenre = genreRepository.findById(FIRST_GENRE_ID).get();
//        assertThat(expectedGenre).usingRecursiveComparison().isEqualTo(currentGenre);
//    }
//
//    @Test
//    @DisplayName("Должен вернуть все жанры")
//    void findAll() {
//        List<Genre> genres = genreRepository.findAll();
//        assertThat(genres).hasSize(EXPECTED_NUMBER_OF_GENRE);
//    }
//
//    @Test
//    @DisplayName("Должен сохранить новый жанр")
//    void save() {
//        Genre genre = new Genre();
//        genre.setGenreName("Сказка");
//        genre = genreRepository.save(genre);
//        Genre currentGenre = entityManager.find(Genre.class, genre.getId());
//        assertThat(genre).usingRecursiveComparison().isEqualTo(currentGenre);
//    }
//
//    @Test
//    @DisplayName("Должен обновить жанр")
//    void shouldUpdateGenre() {
//        Genre genre = entityManager.find(Genre.class, FIRST_GENRE_ID);
//        entityManager.detach(genre);
//        genre.setGenreName("Агата Кристи");
//        genre = genreRepository.save(genre);
//        Genre currentGenre = entityManager.find(Genre.class, genre.getId());
//        assertThat(genre).usingRecursiveComparison().isEqualTo(currentGenre);
//    }
//}