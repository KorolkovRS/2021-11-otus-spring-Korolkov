package ru.korolkovrs.spring23.rest.dto.converter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.korolkovrs.spring23.domain.Author;
import ru.korolkovrs.spring23.domain.Book;
import ru.korolkovrs.spring23.domain.Comment;
import ru.korolkovrs.spring23.domain.Genre;
import ru.korolkovrs.spring23.rest.dto.RequestBookDto;
import ru.korolkovrs.spring23.rest.dto.ResponseBookDto;
import ru.korolkovrs.spring23.service.AuthorService;
import ru.korolkovrs.spring23.service.BookService;
import ru.korolkovrs.spring23.service.CommentService;
import ru.korolkovrs.spring23.service.GenreService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = {BookDtoConverter.class, CommentDtoConverter.class, AuthorDtoConverter.class, GenreDtoConverter.class})
public class BookDtoConverterTest {
    public static Author EXPECTED_AUTHOR = new Author(1L, "Author");
    public static Genre EXPECTED_GENRE = new Genre(1L, "Genre");
    public static Comment EXPECTED_COMMENT = new Comment(1L, "Comment", null, null, new Book());
    public static Book EXPECTED_BOOK = new Book(1L, "Book", EXPECTED_AUTHOR, EXPECTED_GENRE, List.of(EXPECTED_COMMENT));

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private CommentService commentService;

    @MockBean
    private BookService bookService;

    @Autowired
    private CommentDtoConverter commentDtoConverter;

    @Autowired
    private AuthorDtoConverter authorDtoConverter;

    @Autowired
    private GenreDtoConverter genreDtoConverter;

    @Autowired
    private BookDtoConverter bookDtoConverter;

    @Test
    public void shouldCorrectConvertRequestBookDtoToDomain() {
        given(authorService.findById(1L)).willReturn(Optional.of(EXPECTED_AUTHOR));
        given(genreService.findById(1L)).willReturn(Optional.of(EXPECTED_GENRE));

        RequestBookDto requestBookDto = new RequestBookDto(3L, "new Book", 1L, 1L);

        Book book = bookDtoConverter.toDomainObject(requestBookDto);

        assertAll(
                () -> assertEquals(book.getId(), requestBookDto.getId()),
                () -> assertEquals(book.getTitle(), requestBookDto.getTitle()),
                () -> assertEquals(book.getAuthor().getId(), requestBookDto.getAuthorId()),
                () -> assertEquals(book.getGenre().getId(), requestBookDto.getGenreId())
        );
    }

    @Test
    public void shouldCorrectConvertDomainTotoResponseDtoWithoutComments() {
        ResponseBookDto responseBookDto = bookDtoConverter.toResponseDtoWithoutComments(EXPECTED_BOOK);

        assertAll(
                () -> assertEquals(responseBookDto.getId(), EXPECTED_BOOK.getId()),
                () -> assertEquals(responseBookDto.getTitle(), EXPECTED_BOOK.getTitle()),
                () -> assertEquals(responseBookDto.getAuthor(), authorDtoConverter.toDto(EXPECTED_AUTHOR)),
                () -> assertEquals(responseBookDto.getGenre(), genreDtoConverter.toDto(EXPECTED_GENRE)),
                () -> assertEquals(responseBookDto.getComments(), null)
        );
    }

    @Test
    public void shouldCorrectConvertDomainTotoResponseDtoIncludeComments() {
        given(commentService.findByBook(EXPECTED_BOOK)).willReturn(List.of(EXPECTED_COMMENT));

        ResponseBookDto responseBookDto = bookDtoConverter.toResponseDtoIncludeComments(EXPECTED_BOOK);

        assertAll(
                () -> assertEquals(responseBookDto.getId(), EXPECTED_BOOK.getId()),
                () -> assertEquals(responseBookDto.getTitle(), EXPECTED_BOOK.getTitle()),
                () -> assertEquals(responseBookDto.getAuthor(), authorDtoConverter.toDto(EXPECTED_AUTHOR)),
                () -> assertEquals(responseBookDto.getGenre(), genreDtoConverter.toDto(EXPECTED_GENRE)),
                () -> assertEquals(responseBookDto.getComments(), List.of(EXPECTED_COMMENT)
                        .stream()
                        .map(comment -> commentDtoConverter.toResponseCommentDto(comment))
                        .collect(Collectors.toList()))
        );
    }
}
