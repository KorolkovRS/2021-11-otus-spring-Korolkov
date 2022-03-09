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
import ru.korolkovrs.spring20.domain.Genre;
import ru.korolkovrs.spring20.repository.AuthorRepository;
import ru.korolkovrs.spring20.repository.BookRepository;
import ru.korolkovrs.spring20.repository.CommentRepository;
import ru.korolkovrs.spring20.repository.GenreRepository;
import ru.korolkovrs.spring20.rest.dto.RequestBookDto;
import ru.korolkovrs.spring20.rest.dto.ResponseBookDto;
import ru.korolkovrs.spring20.rest.dto.converter.AuthorDtoConverter;
import ru.korolkovrs.spring20.rest.dto.converter.BookDtoConverter;
import ru.korolkovrs.spring20.rest.dto.converter.GenreDtoConverter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@WebFluxTest({BookController.class, BookDtoConverter.class, AuthorDtoConverter.class, GenreDtoConverter.class})
@AutoConfigureDataMongo
class BookControllerTest {
    private final static Author EXPECTED_AUTHOR = new Author("author_id", "author");
    private final static Genre EXPECTED_GENRE = new Genre("genre_id", "genre");

    @Autowired
    private WebTestClient testClient;

    @Autowired
    private BookDtoConverter bookDtoConverter;

    @Autowired
    private BookController bookController;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private CommentRepository commentRepository;

    @Test
    void shouldCorrectGetAllBook() {
        Book book1 = new Book("book1", EXPECTED_AUTHOR, EXPECTED_GENRE);
        Book book2 = new Book("book2", EXPECTED_AUTHOR, EXPECTED_GENRE);
        Flux<Book> bookFlux = Flux.just(book1, book2);

        given(bookRepository.findAll()).willReturn(bookFlux);

        testClient.get()
                .uri("/api/v1/books")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ResponseBookDto.class)
                .value(
                        bookDtos -> assertAll(
                                () -> assertThat(bookDtos.size()).isEqualTo(2),
                                () -> assertThat(bookDtos.get(0)).isEqualTo(bookDtoConverter.toResponseDto(book1)),
                                () -> assertThat(bookDtos.get(1)).isEqualTo(bookDtoConverter.toResponseDto(book2))
                        )
                );
    }

    @Test
    void shouldCorrectGetBookById() {
        String bookId = "book_id";
        Book book = new Book(bookId, "book1", EXPECTED_AUTHOR, EXPECTED_GENRE);
        Mono<Book> bookMono = Mono.just(book);

        given(bookRepository.findById(bookId)).willReturn(bookMono);

        testClient.get()
                .uri("/api/v1/books/" + bookId)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ResponseBookDto.class)
                .value(bookDtos -> assertAll(
                        () -> assertThat(bookDtos.size()).isEqualTo(1),
                        () -> assertThat(bookDtos.get(0)).isEqualTo(bookDtoConverter.toResponseDto(book))
                ));
    }

    @Test
    void shouldCorrectSaveBook() {
        RequestBookDto requestBookDto = new RequestBookDto(null, "title", EXPECTED_AUTHOR.getId(), EXPECTED_GENRE.getId());
        Book expectedBook = new Book(requestBookDto.getId(), requestBookDto.getTitle(), EXPECTED_AUTHOR, EXPECTED_GENRE);
        Mono<Book> bookMono = Mono.just(expectedBook);

        given(authorRepository.findById(EXPECTED_AUTHOR.getId())).willReturn(Mono.just(EXPECTED_AUTHOR));
        given(genreRepository.findById(EXPECTED_GENRE.getId())).willReturn(Mono.just(EXPECTED_GENRE));
        given(bookRepository.save(expectedBook)).willReturn(bookMono);

        testClient.post()
                .uri("/api/v1/books")
                .body(Mono.just(requestBookDto), RequestBookDto.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ResponseBookDto.class)
                .value(result -> assertThat(result).isEqualTo(bookDtoConverter.toResponseDto(expectedBook))
                );
    }

    @Test
    void shouldCorrectUpdateBook() {
        RequestBookDto requestBookDto = new RequestBookDto("bookId", "title", EXPECTED_AUTHOR.getId(), EXPECTED_GENRE.getId());
        Book expectedBook = new Book(requestBookDto.getId(), requestBookDto.getTitle(), EXPECTED_AUTHOR, EXPECTED_GENRE);
        Mono<Book> bookMono = Mono.just(expectedBook);

        given(authorRepository.findById(EXPECTED_AUTHOR.getId())).willReturn(Mono.just(EXPECTED_AUTHOR));
        given(genreRepository.findById(EXPECTED_GENRE.getId())).willReturn(Mono.just(EXPECTED_GENRE));
        given(bookRepository.save(expectedBook)).willReturn(bookMono);

        testClient.put()
                .uri("/api/v1/books")
                .body(Mono.just(requestBookDto), RequestBookDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResponseBookDto.class)
                .value(result -> assertThat(result).isEqualTo(bookDtoConverter.toResponseDto(expectedBook))
                );
    }

    @Test
    void shouldCorrectDeleteBookById() {
        String bookId = "book_id";

        given(bookRepository.deleteById(bookId)).willReturn(Mono.empty());
        given(commentRepository.deleteAllByBookId(bookId)).willReturn(Mono.empty());

        testClient.delete()
                .uri("/api/v1/books/" + bookId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class);
    }
}
