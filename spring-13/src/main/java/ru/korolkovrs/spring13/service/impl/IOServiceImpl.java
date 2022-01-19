package ru.korolkovrs.spring13.service.impl;

import org.springframework.stereotype.Service;
import ru.korolkovrs.spring13.factory.InputStreamFactory;
import ru.korolkovrs.spring13.factory.OutputStreamFactory;
import ru.korolkovrs.spring13.service.IOService;

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
        return scanner.nextLine();
    }

    @Override
    public void out(String s, Object... args) {
        printStream.println(String.format(s, args));
    }
}
