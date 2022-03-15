package ru.korolkovrs.spring23.repository.spec.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring23.domain.Author;
import ru.korolkovrs.spring23.domain.Genre;
import ru.korolkovrs.spring23.exception.IllegalResponseDataException;
import ru.korolkovrs.spring23.exception.ResourceNotFoundException;
import ru.korolkovrs.spring23.service.AuthorService;
import ru.korolkovrs.spring23.service.GenreService;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class BookFilterBuilder {
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookFilter buildBookFilter(Map<String, String> filterParams) {
        List<Author> authors = null;
        Genre genre = null;
        String title = null;

        if (filterParams.containsKey(BookFilterParams.TITLE.getValue())) {
            title = filterParams.get(BookFilterParams.TITLE.getValue());
        }

        if (filterParams.containsKey(BookFilterParams.AUTHOR_NAME.getValue())) {
            String authorNamePattern = filterParams.get(BookFilterParams.AUTHOR_NAME.getValue());
            authors = authorService.findByName(authorNamePattern);
        }

        if (filterParams.containsKey(BookFilterParams.GENRE_ID.getValue())) {
            try {
                genre = getGenreFromFilterParams(filterParams);
            } catch (NumberFormatException ex) {
                throw new IllegalResponseDataException("genreId should be number");
            }
        }
        return new BookFilter(
                title,
                authors,
                genre
        );
    }

    private Genre getGenreFromFilterParams(Map<String, String> filterParams) {
        Long genreId = Long.valueOf(filterParams.get(BookFilterParams.GENRE_ID.getValue()));
        Genre genre = genreService.findById(genreId).orElseThrow(
                () -> new ResourceNotFoundException(String.format("Genre with id=%d not found", genreId)));
        return genre;
    }

    public enum BookFilterParams {
        TITLE("title"),
        AUTHOR_NAME("authorName"),
        GENRE_ID("genreId");

        private final String value;

        BookFilterParams(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
