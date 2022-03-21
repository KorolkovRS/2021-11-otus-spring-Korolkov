package ru.korolkovrs.spring23.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseCommentDto {
    private Long id;

    private String text;

    private Date createdAt;

    private Date updatedAt;
}
