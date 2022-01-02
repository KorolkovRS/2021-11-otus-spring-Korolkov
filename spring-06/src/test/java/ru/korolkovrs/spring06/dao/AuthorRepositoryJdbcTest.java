package ru.korolkovrs.spring06.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.korolkovrs.spring06.dao.impl.AuthorRepositoryJpa;
import ru.korolkovrs.spring06.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Author dao")
@JdbcTest
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJdbcTest {
    private static final Long EXISTING_AUTHOR_ID_1 = 1L;
    private static final String EXISTING_AUTHOR_NAME_1 = "Л.Н. Толстой";
    private static final Long EXISTING_AUTHOR_ID_2 = 2L;
    private static final String EXISTING_AUTHOR_NAME_2 = "Роджер Желязны";

    @Autowired
    private AuthorRepositoryJpa authorDao;

//    @Test
//    @DisplayName("Должен вернуть автора по id")
//    void shouldReturnAuthorById() {
//        Author existAuthor = new Author(EXISTING_AUTHOR_ID_2, EXISTING_AUTHOR_NAME_2);
//        Author author = authorDao.findById(existAuthor.getId());
//        assertThat(author).usingRecursiveComparison().isEqualTo(existAuthor);
//    }
//
//    @Test
//    @DisplayName("Должен вернуть всех авторов")
//    void shouldReturnAllAuthor() {
//        List<Author> existAuthors = List.of(new Author(EXISTING_AUTHOR_ID_1, EXISTING_AUTHOR_NAME_1), new Author(EXISTING_AUTHOR_ID_2, EXISTING_AUTHOR_NAME_2));
//        List<Author> authors = authorDao.findAll();
//        assertThat(authors).usingRecursiveComparison().isEqualTo(existAuthors);
//    }
//
//    @Test
//    @DisplayName("Должен сохранить нового автора")
//    void shouldSaveAuthor() {
//        Author newAuthor = new Author();
//        newAuthor.setName("Станислав Лем");
//        authorDao.save(newAuthor);
//        Author author = authorDao.findById(3L);
//        assertThat(author.getName()).isEqualTo(newAuthor.getName());
//    }
//
//    @Test
//    @DisplayName("Должен вернуть автора по name")
//    void shouldReturnAuthorByName() {
//        Author existAuthor = new Author(EXISTING_AUTHOR_ID_2, EXISTING_AUTHOR_NAME_2);
//        Author author = authorDao.findByName(existAuthor.getName());
//        assertThat(author).usingRecursiveComparison().isEqualTo(existAuthor);
//    }
}