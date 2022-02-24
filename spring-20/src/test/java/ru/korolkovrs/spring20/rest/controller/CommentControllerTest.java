package ru.korolkovrs.spring20.rest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.korolkovrs.spring20.domain.Author;
import ru.korolkovrs.spring20.domain.Book;
import ru.korolkovrs.spring20.domain.Comment;
import ru.korolkovrs.spring20.domain.Genre;
import ru.korolkovrs.spring20.repository.BookRepository;
import ru.korolkovrs.spring20.repository.CommentRepository;
import ru.korolkovrs.spring20.rest.dto.RequestCommentDto;
import ru.korolkovrs.spring20.rest.dto.ResponseCommentDto;
import ru.korolkovrs.spring20.rest.dto.converter.CommentDtoConverter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@WebFluxTest({CommentController.class, CommentDtoConverter.class})
@AutoConfigureDataMongo
class CommentControllerTest {
    private static final Book EXPECTED_BOOK = new Book("bookId", "title", new Author(), new Genre());
    ;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private CommentDtoConverter commentDtoConverter;

    @Autowired
    private CommentController commentController;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private BookRepository bookRepository;

    @Test
    void shouldCorrectFindByBookId() {
        Comment expectedComment = new Comment("commentId", "text", EXPECTED_BOOK);
        Flux<Comment> commentMono = Flux.just(expectedComment);

        given(commentRepository.findByBookId(EXPECTED_BOOK.getId())).willReturn(commentMono);

        webTestClient.get()
                .uri("/api/v1/comments?bookId=" + EXPECTED_BOOK.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ResponseCommentDto.class)
                .value(result ->
                        assertAll(
                                () -> assertThat(result.size()).isEqualTo(1),
                                () -> assertThat(result.get(0)).isEqualTo(commentDtoConverter.toResponseCommentDto(expectedComment))
                        )
                );
    }

    @Test
    void shouldCorrectSaveComment() {
        RequestCommentDto requestCommentDto = new RequestCommentDto(null, "text", EXPECTED_BOOK.getId());
        Comment expectedComment = new Comment(requestCommentDto.getId(), requestCommentDto.getText(), EXPECTED_BOOK);

        given(commentRepository.save(expectedComment)).willReturn(Mono.just(expectedComment));
        given(bookRepository.findById(requestCommentDto.getBookId())).willReturn(Mono.just(EXPECTED_BOOK));

        webTestClient.post()
                .uri("/api/v1/comments")
                .body(Mono.just(requestCommentDto), RequestCommentDto.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBodyList(ResponseCommentDto.class)
                .value(result -> assertAll(
                        () -> assertThat(result.size()).isEqualTo(1),
                        () -> assertThat(result.get(0)).isEqualTo(commentDtoConverter.toResponseCommentDto(expectedComment))
                ));
    }

    @Test
    void shouldCorrectUpdateComment() {
        RequestCommentDto requestCommentDto = new RequestCommentDto("id", "text", EXPECTED_BOOK.getId());
        Comment expectedComment = new Comment(requestCommentDto.getId(), requestCommentDto.getText(), EXPECTED_BOOK);

        given(commentRepository.save(expectedComment)).willReturn(Mono.just(expectedComment));
        given(bookRepository.findById(requestCommentDto.getBookId())).willReturn(Mono.just(EXPECTED_BOOK));

        webTestClient.put()
                .uri("/api/v1/comments")
                .body(Mono.just(requestCommentDto), RequestCommentDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ResponseCommentDto.class)
                .value(result -> assertAll(
                        () -> assertThat(result.size()).isEqualTo(1),
                        () -> assertThat(result.get(0)).isEqualTo(commentDtoConverter.toResponseCommentDto(expectedComment))
                ));
    }

    @Test
    void shouldCorrectDeleteBookById() {
        String commentId = "id";

        given(commentRepository.deleteById(commentId)).willReturn(Mono.empty());

        webTestClient.delete()
                .uri("/api/v1/comments/" + commentId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class);
    }
}