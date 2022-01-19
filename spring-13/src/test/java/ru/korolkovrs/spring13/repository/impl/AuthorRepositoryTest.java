package ru.korolkovrs.spring13.repository.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.korolkovrs.spring13.domain.Author;
import ru.korolkovrs.spring13.repository.AuthorRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Author repository")
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private MongoTemplate template;

    @AfterEach
    void cleanUp() {
        authorRepository.deleteAll();
    }

    @Test
    @DisplayName("Должен искать авторов, содержащих в названии заданную подстроку без учета регистра")
    void shouldFindBooksByTitleIgnoreCase() {
        template.save(new Author("Гарри гаррисон"));
        template.save(new Author("Галилео галилей"));
        template.save(new Author("Станислав Лем"));

        String pattern = "га";
        List<Author> authors = authorRepository.findAllByNameIgnoreCaseContaining(pattern);
        assertThat(authors).hasSize(2);
    }
}
