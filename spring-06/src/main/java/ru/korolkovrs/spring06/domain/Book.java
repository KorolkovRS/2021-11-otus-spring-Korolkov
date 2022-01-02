package ru.korolkovrs.spring06.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    private Long id;
    private String title;
    private Author author;
    private Genre genre;
}
