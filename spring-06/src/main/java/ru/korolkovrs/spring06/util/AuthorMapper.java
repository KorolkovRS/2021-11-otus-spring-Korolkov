package ru.korolkovrs.spring06.util;

import org.springframework.jdbc.core.RowMapper;
import ru.korolkovrs.spring06.domain.Author;
import ru.korolkovrs.spring06.exception.DataReadException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) {
        try {
            Long id = rs.getLong("author_id");
            String name = rs.getString("name");
            return new Author(id, name);
        } catch (SQLException e) {
            throw new DataReadException(e);
        }
    }
}
