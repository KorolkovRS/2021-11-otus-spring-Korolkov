package ru.korolkovrs.spring.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring.domain.Answer;
import ru.korolkovrs.spring.domain.Question;
import ru.korolkovrs.spring.i18n_util.Internalizer;

@Component
@RequiredArgsConstructor
final public class QuestionToStringConverter implements Converter<Question, String> {
    private final Converter<Answer, String> answerStringConverter;
    private final Internalizer internalizer;

    @Override
    public String convert(Question question) {
        StringBuilder sb = new StringBuilder(String.format(internalizer.internalizeMessage("questionToStringConverter.question"), question.getId(), question.getName()));
        if (question.getAnswers() != null) {
            for (int i = 0; i < question.getAnswers().size(); i++) {
                sb.append(i + 1 + ") " + answerStringConverter.convert(question.getAnswers().get(i)) + "\n");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}



