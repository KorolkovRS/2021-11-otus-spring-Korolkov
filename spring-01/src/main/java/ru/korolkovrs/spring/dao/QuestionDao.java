package ru.korolkovrs.spring.dao;

import ru.korolkovrs.spring.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getAll();
}
