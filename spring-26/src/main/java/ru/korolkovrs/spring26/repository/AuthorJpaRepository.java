package ru.korolkovrs.spring26.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.korolkovrs.spring26.domain.jpa.AuthorJpa;

@Repository
public interface AuthorJpaRepository extends JpaRepository<AuthorJpa, Long> {
}
