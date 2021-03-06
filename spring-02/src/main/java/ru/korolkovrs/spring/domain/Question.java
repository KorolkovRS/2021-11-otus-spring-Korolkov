package ru.korolkovrs.spring.domain;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.korolkovrs.spring.converter.TextToAnswerConverter;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @CsvBindByName(column = "id", required = true)
    private int id;

    @CsvBindByName(column = "question", required = true)
    private String name;

    @CsvBindAndSplitByName(column = "answer", elementType = Answer.class, splitOn = "\\|", converter = TextToAnswerConverter.class)
    private List<Answer> answers;
}


