package ru.korolkovrs.spring11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.korolkovrs.spring11.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
