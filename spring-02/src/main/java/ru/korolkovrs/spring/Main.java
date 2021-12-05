package ru.korolkovrs.spring;

import org.springframework.context.annotation.*;
import ru.korolkovrs.spring.service.TestService;

@Configuration
@ComponentScan
@PropertySource("classpath:spring-02.properties")
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        TestService testService = context.getBean(TestService.class);
        testService.test();
    }
}
