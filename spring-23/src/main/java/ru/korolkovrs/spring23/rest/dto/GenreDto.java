package ru.korolkovrs.spring23.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GenreDto {
    private Long id;

    private String genreName;
}
