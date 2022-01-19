package ru.korolkovrs.spring13.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.korolkovrs.spring13.domain.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {
    List<Author> findAllByNameIgnoreCaseContaining(String name);
}
