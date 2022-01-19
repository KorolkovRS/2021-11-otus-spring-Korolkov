package ru.korolkovrs.spring13.shell.service;

public interface ShellCommentService {
    void getCommentById();
    void getAllComment();
    void addComment();
    void updateComment();
    void removeComment();
    void getCommentByBook();
}
