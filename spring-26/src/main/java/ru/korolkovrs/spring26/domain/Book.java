package ru.korolkovrs.spring26.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("book")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {

    public Book(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    @Id
    private String id;

    private String title;

    private Author author;

    private Genre genre;
}
