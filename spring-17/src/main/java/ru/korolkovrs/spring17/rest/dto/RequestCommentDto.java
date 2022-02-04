package ru.korolkovrs.spring17.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCommentDto {
    private Long id;
    private String text;
    private Long bookId;
}
