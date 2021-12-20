package ru.korolkovrs.spring.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.korolkovrs.spring.domain.Author;
import ru.korolkovrs.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@DisplayName("Genre mapper")
class GenreMapperTest {
    @Mock
    ResultSet rs = mock(ResultSet.class);

    @Test
    @DisplayName("Должен собирать жанр из resultSet")
    void shouldMapResultSetToGenre() throws SQLException {
        given(rs.getInt("genre_id")).willReturn(1);
        given(rs.getString("genre_name")).willReturn("Genre");

        GenreMapper genreMapper = new GenreMapper();
        Genre genre = genreMapper.mapRow(rs, 2);
        Genre correctGenre = new Genre(1, "Genre");
        assertEquals(genre, correctGenre);
    }
}