package ru.korolkovrs.spring26.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("comment")
@Data
@NoArgsConstructor
public class Comment {

    public Comment(String text, Book book) {
        this.text = text;
        this.book = book;
    }

    @Id
    private String id;

    private String text;

    private Book book;

    private Date createdAt;

    private Date updatedAt;
}
