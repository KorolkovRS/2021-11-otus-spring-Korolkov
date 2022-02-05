package ru.korolkovrs.spring16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.korolkovrs.spring16.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
