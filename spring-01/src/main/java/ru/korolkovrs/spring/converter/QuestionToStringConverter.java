package ru.korolkovrs.spring.converter;

import org.springframework.core.convert.converter.Converter;
import ru.korolkovrs.spring.domain.Question;

final public class QuestionToStringConverter implements Converter<Question, String> {
    @Override
    public String convert(Question question) {
        StringBuilder sb = new StringBuilder(String.format("Question №%d: %s\nAnswers:\n", question.getId(), question.getName()));
        if (question.getAnswers() != null) {
            for (int i = 0; i < question.getAnswers().size(); i++) {
                sb.append(i + 1 + ") " + question.getAnswers().get(i) + "\n");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}



