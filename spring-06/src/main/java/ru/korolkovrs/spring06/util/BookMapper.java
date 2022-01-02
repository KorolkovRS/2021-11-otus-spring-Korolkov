package ru.korolkovrs.spring06.util;

import org.springframework.jdbc.core.RowMapper;
import ru.korolkovrs.spring06.domain.Author;
import ru.korolkovrs.spring06.domain.Book;
import ru.korolkovrs.spring06.domain.Genre;
import ru.korolkovrs.spring06.exception.DataReadException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) {
        Author author = new AuthorMapper().mapRow(rs, rowNum);
        Genre genre = new GenreMapper().mapRow(rs, rowNum);
        try {
            Long id = rs.getLong("book_id");
            String title = rs.getString("title");
            return new Book(id, title, author, genre);
        } catch (SQLException throwables) {
            throw new DataReadException(throwables);
        }
    }
}
