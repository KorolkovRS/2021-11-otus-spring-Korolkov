package ru.korolkovrs.spring.controller;

import lombok.RequiredArgsConstructor;
import ru.korolkovrs.spring.dao.QuestionDao;
import ru.korolkovrs.spring.domain.Question;

import java.util.List;

@RequiredArgsConstructor
public class QuestionControllerImpl implements QuestionController {
    private final QuestionDao dao;

    @Override
    public void getAll() {
        List<Question> questions = dao.getAll();
        for (Question q: questions) {
            System.out.println(q);
            System.out.println("**************************");
        }
    }
}
