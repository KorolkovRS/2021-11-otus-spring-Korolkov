package ru.korolkovrs.spring06.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.korolkovrs.spring06.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Author mapper")
class AuthorMapperTest {
    @Mock
    private ResultSet rs = mock(ResultSet.class);

    @Test
    @DisplayName("Должен собирать автора из resultSet")
    void shouldMapResultSetToAuthor() throws SQLException {
        given(rs.getLong("author_id")).willReturn(1L);
        given(rs.getString("name")).willReturn("Author");

        AuthorMapper authorMapper = new AuthorMapper();
        Author author = authorMapper.mapRow(rs, 2);
        Author correctAuthor = new Author(1L, "Author");
        assertEquals(author, correctAuthor);
    }
}