package ru.korolkovrs.spring.utils;

import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;

@RequiredArgsConstructor
public class FileResourcesUtils {
    private final String fileName = "que";

    public synchronized InputStream getFileFromResourceAsStream() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

//        Reader reader = new InputStreamReader();
//        FileReader fileReader = new FileReader(reader)
//
//        Reader reader = new FileReader();

//        Reader reader = new FileReader();


        Reader in = new FileReader("book.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .parse(in);
        for (CSVRecord record : records) {
//            String author = record.get("author");
//            String title = record.get("title");
            System.out.println(record.get("id"));
            System.out.println(record.get("question"));
            System.out.println(record.get("answer1"));
            System.out.println(record.get("answer2"));
            System.out.println(record.get("answer3"));
            System.out.println(record.get("answer4"));
        }






        if (inputStream == null) {
            throw new IllegalArgumentException("Resource not found! " + fileName);
        }
        return inputStream;
    }
}
