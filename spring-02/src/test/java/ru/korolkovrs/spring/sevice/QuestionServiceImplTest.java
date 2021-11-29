package ru.korolkovrs.spring.sevice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.korolkovrs.spring.dao.QuestionDao;
import ru.korolkovrs.spring.domain.Answer;
import ru.korolkovrs.spring.domain.Question;
import ru.korolkovrs.spring.service.QuestionService;
import ru.korolkovrs.spring.service.QuestionServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

@DisplayName("QuestionServiceImpl")
public class QuestionServiceImplTest {
    private QuestionService questionService;
    private QuestionDao questionDao = mock(QuestionDao.class);
    private List<Question> questions;

    @BeforeEach
    void init() {
        List<Answer> answers1 = new ArrayList<>();
        answers1.add(new Answer("One", true));
        answers1.add(new Answer("Two", false));
        List<Answer> answers2 = new ArrayList<>();
        answers2.add(new Answer("Three", true));

        questions = new ArrayList<>();
        questions.add(new Question(1, "One", answers1));
        questions.add(new Question(2, "Two", answers2));

        given(questionDao.getAll()).willReturn(questions);
        questionService = new QuestionServiceImpl(questionDao);
    }

    @Test
    @DisplayName("getAll() Returns the list of questions correctly")
    void shouldReturnQuestionListCorrect() {
        assertEquals(questions, questionService.getAll());
    }
}

