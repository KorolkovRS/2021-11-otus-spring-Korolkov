package ru.korolkovrs.spring23.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestBookDto {
    private Long id;

    @NotNull(message = "title is mandatory")
    @NotBlank(message = "title should not be empty")
    @Size(max = 30, message = "title should has expected size")
    private String title;

    @NotNull(message = "authorId is mandatory")
    private Long authorId;

    @NotNull(message = "genreId is mandatory")
    private Long genreId;
}
