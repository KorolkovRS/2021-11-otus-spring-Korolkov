package ru.korolkovrs.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.korolkovrs.spring.domain.Author;
import ru.korolkovrs.spring.util.AuthorMapper;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public Author getById(Long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject("SELECT author_id, name FROM author WHERE author_id = :id",
                params, new AuthorMapper());
    }

    @Override
    public Author getByName(String name) {
        Map<String, String> params = Collections.singletonMap("name", name);
        return namedParameterJdbcOperations.queryForObject("SELECT author_id, name FROM author WHERE name = :name", params, new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query("SELECT author_id, name FROM author", new AuthorMapper());
    }

    @Override
    public void save(Author author) {
        Map<String, Object> params = Map.of("name", author.getName());
        namedParameterJdbcOperations.update("INSERT INTO author(name) VALUES(:name)", params);
    }
}
