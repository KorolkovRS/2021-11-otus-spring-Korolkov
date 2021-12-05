package ru.korolkovrs.spring.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring.domain.Answer;

@Component
public class UserAnswerToStringConverter implements Converter<Answer, String> {
    @Override
    public String convert(Answer answer) {
        return answer.getName();
    }
}
