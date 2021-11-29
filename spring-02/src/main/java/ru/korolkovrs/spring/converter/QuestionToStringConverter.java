package ru.korolkovrs.spring.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring.domain.Answer;
import ru.korolkovrs.spring.domain.Question;

@Component
@RequiredArgsConstructor
final public class QuestionToStringConverter implements Converter<Question, String> {
    private final Converter<Answer, String> answerStringConverter;

    @Override
    public String convert(Question question) {
        StringBuilder sb = new StringBuilder(String.format("Question №%d: %s\nAnswers:\n", question.getId(), question.getName()));
        if (question.getAnswers() != null) {
            for (int i = 0; i < question.getAnswers().size(); i++) {
                sb.append(i + 1 + ") " + answerStringConverter.convert(question.getAnswers().get(i)) + "\n");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}



