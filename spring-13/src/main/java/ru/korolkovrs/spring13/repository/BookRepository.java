package ru.korolkovrs.spring13.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.korolkovrs.spring13.domain.Author;
import ru.korolkovrs.spring13.domain.Book;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAllByAuthor(Author author);

    List<Book> findAllByTitleIgnoreCaseContaining(String title);
}
