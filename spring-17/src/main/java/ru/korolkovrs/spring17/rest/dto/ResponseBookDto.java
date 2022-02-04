package ru.korolkovrs.spring17.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseBookDto {
    private Long id;

    private String title;

    private AuthorDto author;

    private GenreDto genre;

    private List<ResponseCommentDto> comments;
}
