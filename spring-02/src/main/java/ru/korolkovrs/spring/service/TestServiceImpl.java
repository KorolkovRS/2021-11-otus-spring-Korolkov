package ru.korolkovrs.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring.converter.QuestionToStringConverter;
import ru.korolkovrs.spring.domain.Question;
import ru.korolkovrs.spring.domain.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final UserService userService;
    private final QuestionService questionService;
    private final IOService ioService;
    private final QuestionToStringConverter converter;

    @Override
    public void test() {
        User user = userService.getUser();
        List<Question> questions = questionService.getAll();
        int score = 0;

        for(Question question: questions) {
            ioService.out(converter.convert(question));
            int answer;
            while (true) {
                answer = checkUserAnswer(question);
                if (answer != -1) {
                    break;
                }
            }
            if (question.getAnswers().get(answer).isCorrect()) {
                score++;
            }
        }
        ioService.out(String.format("User: %s %s\nScore: %d", user.getName(), user.getSurname(), score));
    }

    private int checkUserAnswer(Question q) {
        try {
            ioService.out("Enter the correct answer from 1 to " + q.getAnswers().size());
            int answer = Integer.valueOf(ioService.input());
            if (answer > 0 && answer <= q.getAnswers().size()) {
                return answer - 1;
            }
            throw new RuntimeException();
        } catch (RuntimeException e) {
            ioService.out("Incorrect answer format");
            return -1;
        }
    }
}
