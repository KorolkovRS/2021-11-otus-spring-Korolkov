package ru.korolkovrs.spring.dao;

import ru.korolkovrs.spring.domain.Question;

import java.util.List;
import java.util.Locale;

public interface QuestionDao {
    List<Question> getAllWithLocale(Locale locale);
}
