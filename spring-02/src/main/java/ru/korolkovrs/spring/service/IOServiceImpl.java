package ru.korolkovrs.spring.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Scanner;
import javax.annotation.PreDestroy;

@Service
public class IOServiceImpl implements IOService {
    private final Scanner scanner;

    public IOServiceImpl() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String get() {
        return scanner.next();
    }

    @Override
    public void print(String s) {
        System.out.println(s);
    }

    @PreDestroy
    public void closeResource() {
        scanner.close();
    }
}
