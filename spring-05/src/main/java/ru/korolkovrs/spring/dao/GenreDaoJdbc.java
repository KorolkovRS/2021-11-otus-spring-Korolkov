package ru.korolkovrs.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring.domain.Genre;
import ru.korolkovrs.spring.util.GenreMapper;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public Genre getById(Long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject("SELECT genre_id, genre_name FROM genre WHERE genre_id = :id", params, new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        return namedParameterJdbcOperations.query("SELECT genre_id, genre_name FROM genre", new GenreMapper());
    }

    @Override
    public void save(Genre genre) {
        Map<String, Object> params = Map.of("title", genre.getGenreName());
        namedParameterJdbcOperations.update("INSERT INTO genre(genre_name) VALUES(:title)", params);
    }
}
