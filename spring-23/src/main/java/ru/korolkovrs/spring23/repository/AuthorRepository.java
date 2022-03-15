package ru.korolkovrs.spring23.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.korolkovrs.spring23.domain.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {
    List<Author> findAuthorByNameContainingIgnoreCase(String name);
}
