package ru.korolkovrs.spring17.rest.dto.converter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring17.domain.Book;
import ru.korolkovrs.spring17.domain.Comment;
import ru.korolkovrs.spring17.exception.ResourceNotFoundException;
import ru.korolkovrs.spring17.rest.dto.RequestCommentDto;
import ru.korolkovrs.spring17.rest.dto.ResponseCommentDto;
import ru.korolkovrs.spring17.service.BookService;
import ru.korolkovrs.spring17.service.CommentService;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommentDtoConverter {
    private final BookService bookService;
    private final CommentService commentService;

    public Comment toDomainObject(RequestCommentDto dto) {
        Book book = bookService.findById(dto.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Book with id=%d not found", dto.getBookId()))
                );
        Comment comment;
        if (dto.getId() == null) {
            comment = new Comment();
        } else {
            comment = commentService.findById(dto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            String.format("Comment with id=%d not found", dto.getId()))
                    );
        }
        comment.setText(dto.getText());
        comment.setBook(book);
        return comment;
    }

    public ResponseCommentDto toResponseCommentDto(Comment comment) {
        ResponseCommentDto commentDto = new ResponseCommentDto(
                comment.getId(),
                comment.getText(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
        return commentDto;
    }
}
