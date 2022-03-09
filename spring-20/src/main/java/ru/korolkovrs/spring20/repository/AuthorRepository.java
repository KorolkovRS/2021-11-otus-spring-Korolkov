package ru.korolkovrs.spring20.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.korolkovrs.spring20.domain.Author;

@Repository
public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
}
