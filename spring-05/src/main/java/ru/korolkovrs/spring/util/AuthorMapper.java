package ru.korolkovrs.spring.util;

import org.springframework.jdbc.core.RowMapper;
import ru.korolkovrs.spring.domain.Author;
import ru.korolkovrs.spring.exception.DataReadException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) {
        try {
            Integer id = rs.getInt("author_id");
            String name = rs.getString("name");
            return new Author(id, name);
        } catch (SQLException e) {
            throw new DataReadException(e);
        }
    }
}
