package ru.korolkovrs.spring16.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;

    @NotBlank(message = "title should not be empty")
    @Size(min = 1, max = 50, message = "title should has expected size")
    private String title;

    private Long author;

    private Long genre;
}
