package ru.korolkovrs.spring.domain;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Question {
    @CsvBindByName(column = "id", required = true)
    private int id;

    @CsvBindByName(column = "question", required = true)
    private String question;

    @CsvBindAndSplitByName(column = "answer", elementType = String.class, splitOn = "\\|", collectionType =  ArrayList.class)
    private List<String> answers;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format("Question №%d: %s\nAnswers:\n", id, question));
        if (answers != null) {
            for (int i = 0; i < answers.size(); i++) {
                sb.append(i + 1 + ") " + answers.get(i) + "\n");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}


