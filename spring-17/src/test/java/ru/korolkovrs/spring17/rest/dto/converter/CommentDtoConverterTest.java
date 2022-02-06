package ru.korolkovrs.spring17.rest.dto.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.korolkovrs.spring17.domain.Book;
import ru.korolkovrs.spring17.domain.Comment;
import ru.korolkovrs.spring17.rest.dto.RequestCommentDto;
import ru.korolkovrs.spring17.rest.dto.ResponseCommentDto;
import ru.korolkovrs.spring17.service.BookService;
import ru.korolkovrs.spring17.service.CommentService;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CommentDtoConverterTest {

    @Mock
    private BookService bookService;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentDtoConverter converter;

    @Test
    public void shouldCorrectConvertDomainToResponseCommentDto() {
        Book expectedBook = new Book(1L, "book", null, null, null);
        Comment comment = new Comment(
                1L,
                "comment",
                new Date(),
                new Date(),
                expectedBook
        );

        ResponseCommentDto dto = converter.toResponseCommentDto(comment);

        assertAll(
                () -> assertEquals(dto.getId(), comment.getId()),
                () -> assertEquals(dto.getText(), comment.getText()),
                () -> assertEquals(dto.getCreatedAt(), comment.getCreatedAt()),
                () -> assertEquals(dto.getUpdatedAt(), comment.getUpdatedAt())
        );
    }

    @Test
    public void shouldCorrectConvertRequestWithIdCommentDtoToDomain() {
        Book expectedBook = new Book(2L, "book", null, null, null);
        RequestCommentDto dto = new RequestCommentDto(1L, "new comment", 2L);
        Comment expectedComment = new Comment(
                1L,
                "comment",
                new Date(),
                new Date(),
                new Book(1L, "book2", null, null, null)
        );

        given(bookService.findById(2L)).willReturn(Optional.of(expectedBook));
        given(commentService.findById(1L)).willReturn(Optional.of(expectedComment));

        Comment comment = converter.toDomainObject(dto);

        assertAll(
                () -> assertEquals(dto.getId(), comment.getId()),
                () -> assertEquals(dto.getText(), comment.getText()),
                () -> assertEquals(expectedComment.getCreatedAt(), comment.getCreatedAt()),
                () -> assertEquals(expectedComment.getUpdatedAt(), comment.getUpdatedAt()),
                () -> assertEquals(dto.getBookId(), comment.getBook().getId())
        );
    }

    @Test
    public void shouldCorrectConvertRequestWithoutIdCommentDtoToDomain() {
        Book expectedBook = new Book(1L, "book", null, null, null);
        RequestCommentDto dto = new RequestCommentDto(null, "new comment", 1L);

        given(bookService.findById(1L)).willReturn(Optional.of(expectedBook));

        Comment comment = converter.toDomainObject(dto);

        assertAll(
                () -> assertEquals(dto.getId(), comment.getId()),
                () -> assertEquals(dto.getText(), comment.getText()),
                () -> assertEquals(dto.getBookId(), comment.getBook().getId())
        );
    }
}
