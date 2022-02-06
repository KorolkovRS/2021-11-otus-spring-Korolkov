package ru.korolkovrs.spring17.repository.spec.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.korolkovrs.spring17.domain.Author;
import ru.korolkovrs.spring17.domain.Genre;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookFilter {
    private String title;
    private List<Author> authors;
    private Genre genre;
}
