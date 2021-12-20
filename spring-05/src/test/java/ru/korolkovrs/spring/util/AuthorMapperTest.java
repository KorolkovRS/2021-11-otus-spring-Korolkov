package ru.korolkovrs.spring.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import ru.korolkovrs.spring.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("Author mapper")
class AuthorMapperTest {
    @Mock
    ResultSet rs = mock(ResultSet.class);

    @Test
    @DisplayName("Должен собирать автора из resultSet")
    void shouldMapResultSetToAuthor() throws SQLException {
        given(rs.getInt("author_id")).willReturn(1);
        given(rs.getString("name")).willReturn("Author");

        AuthorMapper authorMapper = new AuthorMapper();
        Author author = authorMapper.mapRow(rs, 2);
        Author correctAuthor = new Author(1, "Author");
        assertEquals(author, correctAuthor);
    }
}