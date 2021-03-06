package ru.korolkovrs.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import ru.korolkovrs.spring.dao.QuestionDao;
import ru.korolkovrs.spring.domain.Question;

import java.util.List;

@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao dao;
    private final Converter<Question, String> converter;

    @Override
    public void printAll() {
        List<Question> questions = dao.getAll();
        for (Question q: questions) {
            System.out.println(converter.convert(q));
            System.out.println("**************************");
        }
    }
}
