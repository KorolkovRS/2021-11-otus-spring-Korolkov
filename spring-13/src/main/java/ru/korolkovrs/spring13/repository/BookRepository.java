package ru.korolkovrs.spring13.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.korolkovrs.spring13.domain.Author;
import ru.korolkovrs.spring13.domain.Book;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {

    List<Book> findAllByAuthorId(String authorId);

    List<Book> findAllByGenreId(String genreId);

    List<Book> findAllByTitleIgnoreCaseContaining(String title);
}
