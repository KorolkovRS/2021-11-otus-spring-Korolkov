package ru.korolkovrs.spring17.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.korolkovrs.spring17.domain.Author;
import ru.korolkovrs.spring17.domain.Book;
import ru.korolkovrs.spring17.domain.Comment;
import ru.korolkovrs.spring17.domain.Genre;
import ru.korolkovrs.spring17.repository.CommentRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {
    private static Book EXPECTED_BOOK = new Book(
            1L,
            "Book",
            new Author(),
            new Genre(),
            List.of(new Comment())
    );

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void shouldFindCommentById() {
        Comment expectedComment = new Comment(1L, "Comment", new Date(), new Date(), EXPECTED_BOOK);
        given(commentRepository.findById(1L)).willReturn(Optional.of(expectedComment));
        Comment comment = commentService.findById(1L).get();

        assertThat(comment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @Test
    void shouldFindCommentByBook() {
        List<Comment> expectedComments = List.of(new Comment(1L, "Comment", new Date(), new Date(), EXPECTED_BOOK));
        given(commentRepository.findByBookId(EXPECTED_BOOK.getId())).willReturn(expectedComments);
        List<Comment> comments = commentService.findByBook(EXPECTED_BOOK);

        assertThat(comments).usingRecursiveComparison().isEqualTo(expectedComments);
    }

    @Test
    void shouldFindAllComment() {
        List<Comment> expectedComments = List.of(new Comment(1L, "Comment", new Date(), new Date(), EXPECTED_BOOK));
        given(commentRepository.findAll()).willReturn(expectedComments);
        List<Comment> comments = commentService.findAll();

        assertThat(comments).usingRecursiveComparison().isEqualTo(expectedComments);
    }

    @Test
    void shouldSaveComment() {
        Comment comment = new Comment(1L, "Comment", new Date(), new Date(), EXPECTED_BOOK);
        commentService.save(comment);

        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void shouldDeleteCommentById() {
        commentService.deleteById(1L);
        verify(commentRepository, times(1)).deleteById(1L);
    }
}