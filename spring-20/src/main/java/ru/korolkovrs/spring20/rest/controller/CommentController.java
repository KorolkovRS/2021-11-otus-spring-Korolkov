package ru.korolkovrs.spring20.rest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.korolkovrs.spring20.domain.Comment;
import ru.korolkovrs.spring20.exception.IllegalResponseDataException;
import ru.korolkovrs.spring20.repository.BookRepository;
import ru.korolkovrs.spring20.repository.CommentRepository;
import ru.korolkovrs.spring20.rest.dto.RequestCommentDto;
import ru.korolkovrs.spring20.rest.dto.ResponseCommentDto;
import ru.korolkovrs.spring20.rest.dto.converter.CommentDtoConverter;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentDtoConverter commentDtoConverter;
    private final BookRepository bookRepository;

    @GetMapping("/api/v1/comments")
    public Flux<ResponseCommentDto> findByBookId(String bookId) {
        return commentRepository.findByBookId(bookId).map(commentDtoConverter::toResponseCommentDto);
    }

    @PostMapping("/api/v1/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseCommentDto> saveComment(@Valid @RequestBody RequestCommentDto requestCommentDto) {
        if (requestCommentDto.getId() != null) {
            throw new IllegalResponseDataException("Attempt to save an existing comment");
        }
        return collectCommentAndSave(requestCommentDto);
    }

    @PutMapping("/api/v1/comments")
    public Mono<ResponseCommentDto> updateComment(@Valid @RequestBody RequestCommentDto requestCommentDto) {
        if (requestCommentDto.getId() == null) {
            throw new IllegalResponseDataException("The id of the updated comment is not specified");
        }
        return collectCommentAndSave(requestCommentDto);
    }

    @DeleteMapping("/api/v1/comments/{id}")
    public void deleteCommentById(@PathVariable String id) {
        commentRepository.deleteById(id).subscribe();
    }

    private Mono<ResponseCommentDto> collectCommentAndSave(RequestCommentDto requestCommentDto) {
        return bookRepository.findById(requestCommentDto.getBookId())
                .map(book -> new Comment(requestCommentDto.getId(), requestCommentDto.getText(), book))
                .flatMap(c -> commentRepository.save(c))
                .map(c -> commentDtoConverter.toResponseCommentDto(c));
    }
}
