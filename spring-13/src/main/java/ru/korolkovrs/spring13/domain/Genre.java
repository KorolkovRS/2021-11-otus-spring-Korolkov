package ru.korolkovrs.spring13.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("genre")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Genre {

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    @Id
    private String id;

    private String genreName;
}
