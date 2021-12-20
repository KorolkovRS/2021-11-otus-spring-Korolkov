package ru.korolkovrs.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.korolkovrs.spring.domain.Book;
import ru.korolkovrs.spring.util.BookMapper;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public void save(Book book) {
        namedParameterJdbcOperations.update("INSERT INTO book(title, author_id, genre_id) VALUES (:title, :author, :genre)",
                Map.of("title", book.getTitle(), "author", book.getAuthor().getId(), "genre", book.getGenre().getId()));
    }

    @Override
    public Book getById(Long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject("SELECT " +
                    "book_id, " +
                    "title, " +
                    "book.author_id, " +
                    "name, book.genre_id, " +
                    "genre_name " +
                "FROM book " +
                "JOIN author ON book.author_id = author.author_id AND book.book_id = :id " +
                "JOIN genre USING(genre_id) " +
                "ORDER BY book_id", params, new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("SELECT " +
                    "book_id, " +
                    "title, " +
                    "book.author_id, " +
                    "name, book.genre_id, " +
                    "genre_name " +
                "FROM book " +
                "JOIN author USING(author_id) " +
                "JOIN genre USING(genre_id) " +
                "ORDER BY book_id", new BookMapper());
    }

    @Override
    public void update(Book book) {
        namedParameterJdbcOperations.update("UPDATE book SET title = :title, author_id = :author WHERE book_id = :id",
                Map.of("id", book.getId(), "title", book.getTitle(), "author", book.getAuthor().getId()));
    }

    @Override
    public void deleteById(Long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("DELETE FROM book WHERE book_id = :id", params);
    }
}

