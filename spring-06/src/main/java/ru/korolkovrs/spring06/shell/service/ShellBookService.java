package ru.korolkovrs.spring06.shell.service;

public interface ShellBookService {
    void getAllBook();

    void getBookById();

    void getBookByTitle();

    void saveBook();

    void updateBook();

    void delete();
}
