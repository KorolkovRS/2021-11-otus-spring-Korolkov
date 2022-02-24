package ru.korolkovrs.spring20.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseBookDto {
    private String id;

    private String title;

    private AuthorDto author;

    private GenreDto genre;
}
