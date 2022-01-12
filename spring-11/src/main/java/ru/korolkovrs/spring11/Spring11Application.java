package ru.korolkovrs.spring11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spring11Application {
    public static void main(String[] args) {
        SpringApplication.run(Spring11Application.class, args);
    }
}

//    Библиотеку на Spring Data JPA
//
//        Цель:
//        Цель: максимально просто писать слой репозиториев с применением современных подходов
//        Результат: приложение со слоем репозиториев на Spring Data JPA
//
//        Домашнее задание выполняется переписыванием предыдущего на JPA.
//
//        Требования:
//
//        Переписать все репозитории по работе с книгами на Spring Data JPA репозитории.
//        Используйте spring-boot-starter-data-jpa.
//        Кастомные методы репозиториев (или с хитрым @Query) покрыть тестами, используя H2.
//@Transactional рекомендуется ставить на методы сервисов, а не репозиториев.