package ru.korolkovrs.spring26.domain.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("author")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthorJpa {

    public AuthorJpa(String name) {
        this.name = name;
    }

    @Id
    private String id;

    private String name;
}
