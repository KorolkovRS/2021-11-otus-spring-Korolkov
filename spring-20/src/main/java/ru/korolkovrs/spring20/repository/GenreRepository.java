package ru.korolkovrs.spring20.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.korolkovrs.spring20.domain.Genre;

@Repository
public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {
}
