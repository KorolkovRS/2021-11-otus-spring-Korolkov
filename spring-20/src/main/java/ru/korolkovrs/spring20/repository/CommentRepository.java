package ru.korolkovrs.spring20.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.korolkovrs.spring20.domain.Comment;

@Repository
public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {
    Flux<Comment> findByBookId(String id);

    Mono<Void> deleteAllByBookId(String BookId);
}
