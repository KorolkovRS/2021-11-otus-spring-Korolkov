package ru.korolkovrs.spring.service;

import org.springframework.stereotype.Service;
import ru.korolkovrs.spring.factory.InputStreamFactory;
import ru.korolkovrs.spring.factory.OutputStreamFactory;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {
    private final Scanner scanner;
    private final PrintStream printStream;

    public IOServiceImpl(OutputStreamFactory outputStreamFactory, InputStreamFactory inputStreamFactory) {
        scanner = new Scanner(inputStreamFactory.getInputStream());
        printStream = new PrintStream(outputStreamFactory.getOutputStream());
    }

    @Override
    public String input() {
        return scanner.next();
    }

    @Override
    public void out(String s) {
        printStream.println(s);
    }
}
