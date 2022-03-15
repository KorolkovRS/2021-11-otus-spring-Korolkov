package ru.korolkovrs.spring23.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.korolkovrs.spring23.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findAll(Specification<Book> spec);

    @EntityGraph(attributePaths = {"author", "genre", "comments"})
    Optional<Book> findById(Long id);
}
