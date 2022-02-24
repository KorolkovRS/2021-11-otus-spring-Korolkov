package ru.korolkovrs.spring20.rest.dto;

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
    private String id;

    @NotNull(message = "title is mandatory")
    @NotBlank(message = "title should not be empty")
    @Size(max = 30, message = "title should has expected size")
    private String title;

    @NotNull(message = "authorId is mandatory")
    private String authorId;

    @NotNull(message = "genreId is mandatory")
    private String genreId;
}
