package ru.korolkovrs.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Answer {
    private String name;
    private boolean isCorrect;
}
