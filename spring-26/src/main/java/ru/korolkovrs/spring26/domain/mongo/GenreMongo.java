package ru.korolkovrs.spring26.domain.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("genre")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GenreMongo {

    public GenreMongo(String genreName) {
        this.genreName = genreName;
    }

    @Id
    private String id;

    private String genreName;
}