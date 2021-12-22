package ru.korolkovrs.spring.converter;

import com.opencsv.bean.AbstractCsvConverter;
import ru.korolkovrs.spring.domain.Answer;

public class TextToAnswerConverter extends AbstractCsvConverter {

    @Override
    public Object convertToRead(String s) {
        String[] strings = s.split(":", 2);
        Answer answer = new Answer();
        answer.setName(strings[0]);
        if (strings[1].equals("1")) {
            answer.setCorrect(true);
        } else if (strings[1].equals("0")) {
            answer.setCorrect(false);
        } else {
            throw new IllegalArgumentException("Сsv file parsing error. Check the line: " + s);
        }
        return answer;
    }
}
