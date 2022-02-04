package ru.korolkovrs.spring17.rest.dto.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring17.domain.Book;
import ru.korolkovrs.spring17.domain.Comment;
import ru.korolkovrs.spring17.exception.NotFoundException;
import ru.korolkovrs.spring17.rest.dto.RequestCommentDto;
import ru.korolkovrs.spring17.rest.dto.ResponseCommentDto;
import ru.korolkovrs.spring17.service.BookService;
import ru.korolkovrs.spring17.service.CommentService;

@Component
@RequiredArgsConstructor
public class CommentDtoConverter {
    private final BookService bookService;
    private final CommentService commentService;

    public Comment toDomainObject(RequestCommentDto dto) {
        Book book = bookService.findById(dto.getBookId()).orElseThrow(NotFoundException::new);
        Comment comment;
        if (dto.getId() == null) {
            comment = new Comment();
        } else {
            comment = commentService.findById(dto.getId()).orElseThrow(NotFoundException::new);
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
            comment.getCreatedAt()
        );
        return commentDto;
    }
}
