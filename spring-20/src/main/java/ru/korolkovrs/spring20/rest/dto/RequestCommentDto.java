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
public class RequestCommentDto {
    private String id;

    @NotNull(message = "text is mandatory")
    @NotBlank(message = "text should not be empty")
    @Size(max = 255, message = "text should has expected size")
    private String text;

    @NotNull(message = "bookId is mandatory")
    private String bookId;
}
