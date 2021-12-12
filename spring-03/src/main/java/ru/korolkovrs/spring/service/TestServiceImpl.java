package ru.korolkovrs.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring.converter.QuestionToStringConverter;
import ru.korolkovrs.spring.domain.Question;
import ru.korolkovrs.spring.domain.User;
import ru.korolkovrs.spring.policy.IOServiceInternatiolizePolicy;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final UserService userService;
    private final QuestionService questionService;
    private final QuestionToStringConverter converter;
    private final IOServiceInternatiolizePolicy ioServiceInternatiolizePolicy;


    @Override
    public void test() {
        User user = userService.getUser();
        List<Question> questions = questionService.getAll();
        int score = 0;

        for (Question question : questions) {
            ioServiceInternatiolizePolicy.out(converter.convert(question));
            int answer = waitCorrectInputAndGet(question);
            if (question.getAnswers().get(answer).isCorrect()) {
                score++;
            }
        }
        ioServiceInternatiolizePolicy.outWithInternalize("testService.test_result",
                user.getName(), user.getSurname(), score);
    }

    private int waitCorrectInputAndGet(Question q) {
        int answer;
        do {
            answer = checkUserAnswer(q);
        } while (answer == -1);
        return answer;
    }

    private int checkUserAnswer(Question q) {
        try {
            ioServiceInternatiolizePolicy.outWithInternalize("testService.enter_answer", q.getAnswers().size());
            int answer = Integer.parseInt(ioServiceInternatiolizePolicy.input());
            if (answer > 0 && answer <= q.getAnswers().size()) {
                return answer - 1;
            }
            throw new RuntimeException();
        } catch (RuntimeException e) {
            ioServiceInternatiolizePolicy.outWithInternalize("testService.incorrect_format");
            return -1;
        }
    }
}
