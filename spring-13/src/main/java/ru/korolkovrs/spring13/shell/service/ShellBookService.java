package ru.korolkovrs.spring13.shell.service;

public interface ShellBookService {
    void getAllBook();

    void getBookById();

    void getBookByTitle();

    void getBookByAuthor();

    void saveBook();

    void updateBook();

    void delete();
}
