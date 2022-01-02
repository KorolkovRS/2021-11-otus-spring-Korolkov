package ru.korolkovrs.spring06.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.korolkovrs.spring06.dao.impl.GenreRepositoryJpa;
import ru.korolkovrs.spring06.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@DisplayName("Genre dao")
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJdbcTest {
    private static final Long EXISTING_GENRE_ID_1 = 1L;
    private static final String EXISTING_GENRE_1 = "Русская классика";
    private static final Long EXISTING_GENRE_ID_2 = 2L;
    private static final String EXISTING_GENRE_2 = "Фантастика";

    @Autowired
    private GenreRepositoryJpa genreDao;

//    @Test
//    @DisplayName("Должен вернуть жанр по id")
//    void shouldGetGenreById() {
//        Genre existGenre = new Genre(EXISTING_GENRE_ID_1, EXISTING_GENRE_1);
//        Genre genre = genreDao.findById(existGenre.getId());
//        assertThat(genre).usingRecursiveComparison().isEqualTo(existGenre);
//    }
//
//    @Test
//    @DisplayName("Должен вернуть все жанры")
//    void shouldGetAllGenre() {
//        List<Genre> existGenres = List.of(new Genre(EXISTING_GENRE_ID_1, EXISTING_GENRE_1),
//                new Genre(EXISTING_GENRE_ID_2, EXISTING_GENRE_2));
//        List<Genre> genres = genreDao.findAll();
//        assertThat(genres).usingRecursiveComparison().isEqualTo(existGenres);
//    }
//
//    @Test
//    @DisplayName("Должен сохранить новый жанр")
//    void shouldSaveGenre() {
//        Genre newGenre = new Genre();
//        newGenre.setGenreName("Сказка");
//        genreDao.save(newGenre);
//        Genre genre = genreDao.findById(3L);
//        assertThat(genre.getGenreName()).isEqualTo(newGenre.getGenreName());
//    }
}