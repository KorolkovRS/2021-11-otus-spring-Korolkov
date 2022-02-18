package ru.korolkovrs.spring17.repository.spec.filter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.korolkovrs.spring17.domain.Author;
import ru.korolkovrs.spring17.domain.Genre;
import ru.korolkovrs.spring17.service.AuthorService;
import ru.korolkovrs.spring17.service.GenreService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class BookFilterBuilderTest {


    @Mock
    private AuthorService authorService;

    @Mock
    private GenreService genreService;

    @InjectMocks
    private BookFilterBuilder bookFilterBuilder;

    @Test
    public void shouldCorrectBuildBookFilter() {
        String titlePattern = "Преступление";
        String authorNamePattern = "Толстой";
        String genreId = "1";

        Map<String, String> filterParams = Map.of(
                BookFilterBuilder.BookFilterParams.TITLE.getValue(), titlePattern,
                BookFilterBuilder.BookFilterParams.AUTHOR_NAME.getValue(), authorNamePattern,
                BookFilterBuilder.BookFilterParams.GENRE_ID.getValue(), genreId
        );

        List<Author> expectedAuthors = List.of(new Author(1L, "Л.Н. Толстой"));
        Genre expectedGenre = new Genre(1L, "Русская классика");
        BookFilter expectedBookFilter = new BookFilter(titlePattern, expectedAuthors, expectedGenre);

        given(authorService.findByName(authorNamePattern)).willReturn(expectedAuthors);
        given(genreService.findById(1L)).willReturn(Optional.of(expectedGenre));

        BookFilter bookFilter = bookFilterBuilder.buildBookFilter(filterParams);

        assertThat(bookFilter).usingRecursiveComparison().isEqualTo(expectedBookFilter);
    }
}
