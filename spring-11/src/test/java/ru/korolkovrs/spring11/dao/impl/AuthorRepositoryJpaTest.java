package ru.korolkovrs.spring11.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.korolkovrs.spring11.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//@DisplayName("Author dao")
//@DataJpaTest
//@Import(AuthorRepositoryJpa.class)
//class AuthorRepositoryJpaTest {
//    private static final Long FIRST_AUTHOR_ID = 1L;
//    private static final Integer EXPECTED_NUMBER_OF_AUTHORS = 2;
//
//    @Autowired
//    private AuthorRepositoryJpa authorRepository;
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Test
//    @DisplayName("Должен вернуть автора по id")
//    void shouldReturnAuthorById() {
//        Author expectedAuthor = entityManager.find(Author.class, FIRST_AUTHOR_ID);
//        Author currentAuthor = authorRepository.findById(FIRST_AUTHOR_ID).get();
//        assertThat(expectedAuthor).usingRecursiveComparison().isEqualTo(currentAuthor);
//    }
//
//    @Test
//    @DisplayName("Должен вернуть всех авторов")
//    void shouldReturnAllAuthor() {
//        List<Author> authors = authorRepository.findAll();
//        assertThat(authors).hasSize(EXPECTED_NUMBER_OF_AUTHORS);
//    }
//
//    @Test
//    @DisplayName("Должен сохранить нового автора")
//    void shouldSaveAuthor() {
//        Author author = new Author();
//        author.setName("Агата Кристи");
//        author = authorRepository.save(author);
//        Author currentAuthor = entityManager.find(Author.class, author.getId());
//        assertThat(author).usingRecursiveComparison().isEqualTo(currentAuthor);
//    }
//
//    @Test
//    @DisplayName("Должен обновить автора")
//    void shouldUpdateAuthor() {
//        Author author = entityManager.find(Author.class, FIRST_AUTHOR_ID);
//        entityManager.detach(author);
//        author.setName("Агата Кристи");
//        author = authorRepository.save(author);
//        Author currentAuthor = entityManager.find(Author.class, author.getId());
//        assertThat(author).usingRecursiveComparison().isEqualTo(currentAuthor);
//    }
//
//    @Test
//    @DisplayName("Должен вернуть автора по name")
//    void shouldReturnAuthorByName() {
//        Author expectedAuthor = entityManager.find(Author.class, FIRST_AUTHOR_ID);
//        List<Author> authors = authorRepository.findByName(expectedAuthor.getName());
//        assertAll(
//                () -> assertThat(authors).hasSize(1),
//                () -> assertThat(authors.get(0)).usingRecursiveComparison().isEqualTo(expectedAuthor)
//        );
//    }
//}