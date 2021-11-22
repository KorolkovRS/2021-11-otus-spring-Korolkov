package ru.korolkovrs.spring;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        parseCSV();

    }

    static public void parseCSV() throws IOException {
        Reader in = new FileReader("D:\\Programs\\Otus\\2021-11-otus-spring-Korolkov\\spring-01\\src\\main\\resources\\questions.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .parse(in);
        for (CSVRecord record : records) {
//            String author = record.get("author");
//            String title = record.get("title");
            System.out.println(record.get(0));

        }
    }
}
