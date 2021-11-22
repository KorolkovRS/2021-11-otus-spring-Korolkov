package ru.korolkovrs.spring.domain;

import lombok.Data;

import java.util.Map;

@Data
public class Question {
    private Integer id;
    private String question;
    private Map<Integer, String> answerMap;


}
