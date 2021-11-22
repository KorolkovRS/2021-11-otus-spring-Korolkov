package ru.korolkovrs.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.korolkovrs.spring.controller.QuestionController;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        QuestionController controller = context.getBean(QuestionController.class);
        controller.getAll();
    }
}
