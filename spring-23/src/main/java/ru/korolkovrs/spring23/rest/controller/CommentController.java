package ru.korolkovrs.spring23.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.korolkovrs.spring23.domain.Comment;
import ru.korolkovrs.spring23.exception.IllegalResponseDataException;
import ru.korolkovrs.spring23.rest.dto.RequestCommentDto;
import ru.korolkovrs.spring23.rest.dto.ResponseCommentDto;
import ru.korolkovrs.spring23.rest.dto.converter.CommentDtoConverter;
import ru.korolkovrs.spring23.service.CommentService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentDtoConverter commentDtoConverter;

    @PostMapping("/api/v1/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCommentDto saveComment(@Valid @RequestBody RequestCommentDto requestCommentDto) {
        if (requestCommentDto.getId() != null) {
            throw new IllegalResponseDataException("Attempt to save an existing comment");
        }
        Comment comment = commentDtoConverter.toDomainObject(requestCommentDto);
        comment = commentService.save(comment);
        return commentDtoConverter.toResponseCommentDto(comment);
    }

    @PutMapping("/api/v1/comments")
    public ResponseCommentDto updateComment(@Valid @RequestBody RequestCommentDto requestCommentDto) {
        if (requestCommentDto.getId() == null) {
            throw new IllegalResponseDataException("The id of the updated comment is not specified");
        }
        Comment comment = commentDtoConverter.toDomainObject(requestCommentDto);
        comment = commentService.save(comment);
        return commentDtoConverter.toResponseCommentDto(comment);
    }

    @DeleteMapping("/api/v1/comments/{id}")
    public void deleteBookById(@PathVariable Long id) {
        commentService.deleteById(id);
    }
}
