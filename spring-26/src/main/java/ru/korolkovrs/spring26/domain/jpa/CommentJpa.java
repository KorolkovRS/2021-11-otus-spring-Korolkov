package ru.korolkovrs.spring26.domain.jpa;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("comment")
@Data
@NoArgsConstructor
public class CommentJpa {

    public CommentJpa(String text, BookJpa bookJpa) {
        this.text = text;
        this.bookJpa = bookJpa;
    }

    @Id
    private String id;

    private String text;

    private BookJpa bookJpa;

    private Date createdAt;

    private Date updatedAt;
}
