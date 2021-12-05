package ru.korolkovrs.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.korolkovrs.spring.service.TestService;

@SpringBootApplication
public class Spring03Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Spring03Application.class, args);
        TestService service = context.getBean(TestService.class);
        service.test();
    }

}
