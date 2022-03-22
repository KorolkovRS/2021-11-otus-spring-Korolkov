package ru.korolkovrs.spring26.domain.mongo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("comment")
@Data
@NoArgsConstructor
public class CommentMongo {

    public CommentMongo(String text, BookMongo bookMongo) {
        this.text = text;
        this.bookMongo = bookMongo;
    }

    @Id
    private String id;

    private String text;

    private BookMongo bookMongo;

    private Date createdAt;

    private Date updatedAt;
}
