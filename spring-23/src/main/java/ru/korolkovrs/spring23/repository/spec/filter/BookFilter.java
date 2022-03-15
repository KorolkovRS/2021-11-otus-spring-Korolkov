package ru.korolkovrs.spring23.repository.spec.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.korolkovrs.spring23.domain.Author;
import ru.korolkovrs.spring23.domain.Genre;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookFilter {
    private String title;
    private List<Author> authors;
    private Genre genre;
}
