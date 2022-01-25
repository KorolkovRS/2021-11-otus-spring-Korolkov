package ru.korolkovrs.spring13.repository;

import ru.korolkovrs.spring13.domain.Author;
import ru.korolkovrs.spring13.domain.Genre;

public interface BookRepositoryCustom {
    void updateAuthor(Author author);
    void updateGenre(Genre genre);
}
