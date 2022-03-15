package ru.korolkovrs.spring23.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.korolkovrs.spring23.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
