package ru.korolkovrs.spring06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spring06Application {

    public static void main(String[] args) {
        SpringApplication.run(Spring06Application.class, args);
    }

}


//    Переписать приложение для хранения книг на ORM
//
//        Цель:
//        Цель: полноценно работать с JPA + Hibernate для подключения к реляционным БД посредством ORM-фреймворка
//        Результат: Высокоуровневое приложение с JPA-маппингом сущностей
//
//        Домашнее задание выполняется переписыванием предыдущего на JPA.
//
//        Требования:
//
//        Использовать JPA, Hibernate только в качестве JPA-провайдера.
//        Для решения проблемы N+1 можно использовать специфические для Hibernate аннотации @Fetch и @BatchSize.
//        Добавить сущность "комментария к книге", реализовать CRUD для новой сущности.
//        Покрыть репозитории тестами, используя H2 базу данных и соответствующий H2 Hibernate-диалект для тестов.
//        Не забудьте отключить DDL через Hibernate
//@Transactional рекомендуется ставить только на методы сервиса.