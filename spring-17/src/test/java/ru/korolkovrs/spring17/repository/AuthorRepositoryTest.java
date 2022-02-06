package ru.korolkovrs.spring17.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.korolkovrs.spring17.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AuthorRepositoryTest {
    private final static String BOOK_NAME_PATTERN = "толстой";
    private final static Integer EXPECTED_NUMBER_OF_BOOK_WITH_NAME_PATTERN = 1;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void shouldFindAuthorByNameContainingIgnoreCase() {
        List<Author> authors = authorRepository.findAuthorByNameContainingIgnoreCase(BOOK_NAME_PATTERN);
        assertThat(authors).hasSize(EXPECTED_NUMBER_OF_BOOK_WITH_NAME_PATTERN);
    }
}
