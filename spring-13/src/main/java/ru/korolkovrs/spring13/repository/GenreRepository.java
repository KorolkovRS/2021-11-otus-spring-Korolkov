package ru.korolkovrs.spring13.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.korolkovrs.spring13.domain.Genre;

@Repository
public interface GenreRepository extends MongoRepository<Genre, String> {
}
