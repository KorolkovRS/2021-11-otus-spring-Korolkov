package ru.korolkovrs.spring.util;

import org.springframework.jdbc.core.RowMapper;
import ru.korolkovrs.spring.domain.Genre;
import ru.korolkovrs.spring.exception.DataReadException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet rs, int rowNum) {
        try {
            Long id = rs.getLong("genre_id");
            String genreName = rs.getString("genre_name");
            return new Genre(id, genreName);
        } catch (SQLException e) {
            throw new DataReadException(e);
        }
    }
}
