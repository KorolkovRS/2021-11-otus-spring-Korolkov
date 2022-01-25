package ru.korolkovrs.spring13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class Spring13Application {
    public static void main(String[] args) {
        SpringApplication.run(Spring13Application.class, args);
    }
}
