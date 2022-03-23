package ru.korolkovrs.spring26.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.korolkovrs.spring26.domain.mongo.AuthorMongo;

@Repository
public interface AuthorRepository extends MongoRepository<AuthorMongo, String> {
}
