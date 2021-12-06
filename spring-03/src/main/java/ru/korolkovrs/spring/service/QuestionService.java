package ru.korolkovrs.spring.service;

import ru.korolkovrs.spring.domain.Question;

import java.util.List;
import java.util.Locale;

public interface QuestionService {
    List<Question> getAllWithLocale(Locale locale);
}
