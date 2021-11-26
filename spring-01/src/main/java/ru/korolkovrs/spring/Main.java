package ru.korolkovrs.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.korolkovrs.spring.service.QuestionService;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        QuestionService controller = context.getBean(QuestionService.class);
        controller.printAll();
    }
}
