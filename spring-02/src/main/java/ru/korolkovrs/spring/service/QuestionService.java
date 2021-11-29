package ru.korolkovrs.spring.service;

import ru.korolkovrs.spring.domain.Question;

import java.util.List;

public interface QuestionService {
    void printAll();

    List<Question> getAll();
}
