package ru.korolkovrs.spring26.domain.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("book")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookJpa {

    public BookJpa(String title, AuthorJpa authorJpa, GenreJpa genreJpa) {
        this.title = title;
        this.authorJpa = authorJpa;
        this.genreJpa = genreJpa;
    }

    @Id
    private String id;

    private String title;

    private AuthorJpa authorJpa;

    private GenreJpa genreJpa;
}
