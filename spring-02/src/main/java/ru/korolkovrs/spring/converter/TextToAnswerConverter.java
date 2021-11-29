package ru.korolkovrs.spring.converter;

import com.opencsv.bean.AbstractCsvConverter;
import ru.korolkovrs.spring.domain.Answer;

public class TextToAnswerConverter extends AbstractCsvConverter {

    @Override
    public Object convertToRead(String s) {
        String[] strings = s.split(":",2);
        Answer answer = new Answer();
        answer.setName(strings[0]);
        if (strings[1].equals("1")) {
            answer.setCorrect(true);
        } else {
            answer.setCorrect(false);
        }
        return answer;
    }
}
