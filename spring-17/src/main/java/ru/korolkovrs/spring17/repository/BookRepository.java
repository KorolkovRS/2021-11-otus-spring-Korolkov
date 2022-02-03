package ru.korolkovrs.spring17.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.korolkovrs.spring17.domain.Author;
import ru.korolkovrs.spring17.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findAll(Specification<Book> spec);

    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findAllByAuthor(Author author);

    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findAllByOrderById();

    @EntityGraph(attributePaths = {"author", "genre", "comments"})
    Optional<Book> findById(Long id);


    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findAllByTitleIgnoreCaseContaining(String title);
}
