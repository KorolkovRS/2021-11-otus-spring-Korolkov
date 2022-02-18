package ru.korolkovrs.spring17.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.korolkovrs.spring17.domain.Author;
import ru.korolkovrs.spring17.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    void shouldFindAuthorById() {
        Author expectedAuthor = new Author(1L, "Author");
        given(authorRepository.findById(1L)).willReturn(Optional.of(expectedAuthor));
        Author author = authorService.findById(1L).get();

        assertThat(author).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @Test
    void shouldFindAuthorByName() {
        List<Author> expectedAuthors = List.of(new Author(1L, "Author"));
        given(authorRepository.findAuthorByNameContainingIgnoreCase("Author")).willReturn(expectedAuthors);
        List<Author> authors = authorService.findByName("Author");

        assertThat(authors).usingRecursiveComparison().isEqualTo(expectedAuthors);
    }

    @Test
    void shouldFindAllAuthor() {
        List<Author> expectedAuthors = List.of(new Author(1L, "Author"));
        given(authorRepository.findAll()).willReturn(expectedAuthors);
        List<Author> authors = authorService.findAll();

        assertThat(authors).usingRecursiveComparison().isEqualTo(expectedAuthors);
    }

    @Test
    void shouldSaveAuthor() {
        Author author = new Author(1L, "Author");
        authorService.save(author);

        verify(authorRepository, times(1)).save(author);
    }
}