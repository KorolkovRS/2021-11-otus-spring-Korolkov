package ru.korolkovrs.spring.service;

import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {
    private final Scanner scanner;

    public IOServiceImpl() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String input() {
        return scanner.next();
    }

    @Override
    public void out(String s) {
        System.out.println(s);
    }

    @PreDestroy
    public void closeResource() {
        scanner.close();
    }
}
