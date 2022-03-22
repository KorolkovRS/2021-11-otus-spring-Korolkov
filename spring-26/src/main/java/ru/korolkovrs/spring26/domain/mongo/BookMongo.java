package ru.korolkovrs.spring26.domain.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("book")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookMongo {

    public BookMongo(String title, AuthorMongo author, GenreMongo genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    @Id
    private String id;

    private String title;

    private AuthorMongo author;

    private GenreMongo genre;
}
