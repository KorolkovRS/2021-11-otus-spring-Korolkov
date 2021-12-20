package ru.korolkovrs.spring.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.korolkovrs.spring.domain.Author;
import ru.korolkovrs.spring.domain.Book;
import ru.korolkovrs.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@DisplayName("Book mapper")
class BookMapperTest {
    private static final Long EXISTS_BOOK_ID = 1L;
    private static final String EXISTS_BOOK_TITLE = "book";
    private static final Integer EXISTS_AUTHOR_ID = 1;
    private static final String EXISTS_AUTHOR_NAME = "author";
    private static final Integer EXISTS_GENRE_ID = 1;
    private static final String EXISTS_GENRE_NAME = "genre";

    @Mock
    ResultSet rs = mock(ResultSet.class);


    @Test
    @DisplayName("Должен собирать книгу из resultSet")
    void shouldMapResultSetToBook() throws SQLException {
        given(rs.getLong("book_id")).willReturn(EXISTS_BOOK_ID);
        given(rs.getString("title")).willReturn(EXISTS_BOOK_TITLE);
        given(rs.getInt("genre_id")).willReturn(EXISTS_GENRE_ID);
        given(rs.getString("genre_name")).willReturn(EXISTS_GENRE_NAME);
        given(rs.getInt("author_id")).willReturn(EXISTS_AUTHOR_ID);
        given(rs.getString("name")).willReturn(EXISTS_AUTHOR_NAME);

        BookMapper bookMapper = new BookMapper();
        Book book = bookMapper.mapRow(rs, 6);
        Book correctBook = new Book(EXISTS_BOOK_ID, EXISTS_BOOK_TITLE,
                new Author(EXISTS_AUTHOR_ID, EXISTS_AUTHOR_NAME),
                new Genre(EXISTS_GENRE_ID, EXISTS_GENRE_NAME));
        assertEquals(book, correctBook);
    }
}
