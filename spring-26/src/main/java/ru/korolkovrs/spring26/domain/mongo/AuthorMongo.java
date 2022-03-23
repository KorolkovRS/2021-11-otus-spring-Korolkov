package ru.korolkovrs.spring26.domain.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.korolkovrs.spring26.domain.jpa.AuthorJpa;

@Document("author")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthorMongo {

    public AuthorMongo(String name) {
        this.name = name;
    }

    public AuthorMongo(AuthorJpa authorJpa) {
        this.name = name;
    }

    @Id
    private String id;

    private String name;
}
