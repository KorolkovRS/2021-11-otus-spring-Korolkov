package ru.korolkovrs.spring20;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableMongock
@EnableReactiveMongoRepositories
@SpringBootApplication
public class Spring20Application {

    public static void main(String[] args) {
        SpringApplication.run(Spring20Application.class, args);
    }

}
