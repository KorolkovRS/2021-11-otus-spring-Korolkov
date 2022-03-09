package ru.korolkovrs.spring20.domain;

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
    @Id
    private String id;

    private String genreName;

    public Genre(String genreName) {
        this.genreName = genreName;
    }
}
