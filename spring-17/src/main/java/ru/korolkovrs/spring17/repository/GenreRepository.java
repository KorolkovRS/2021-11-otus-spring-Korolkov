package ru.korolkovrs.spring17.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.korolkovrs.spring17.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
