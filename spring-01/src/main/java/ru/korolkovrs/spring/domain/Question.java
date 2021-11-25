package ru.korolkovrs.spring.domain;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Question {
    @CsvBindByName(column = "id", required = true)
    private int id;

    @CsvBindByName(column = "question", required = true)
    private String name;

    @CsvBindAndSplitByName(column = "answer", elementType = String.class, splitOn = "\\|", collectionType =  ArrayList.class)
    private List<String> answers;
}


