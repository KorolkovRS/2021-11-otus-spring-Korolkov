package ru.korolkovrs.spring16.dto.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring16.domain.Book;
import ru.korolkovrs.spring16.domain.Comment;
import ru.korolkovrs.spring16.dto.CommentDto;
import ru.korolkovrs.spring16.exception.NotFoundException;
import ru.korolkovrs.spring16.service.BookService;
import ru.korolkovrs.spring16.service.CommentService;

@Component
@RequiredArgsConstructor
public class CommentDtoConverter {
    private final BookService bookService;
    private final CommentService commentService;

    public Comment toDomainObject(CommentDto dto) {
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
}
