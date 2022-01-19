package ru.korolkovrs.spring13.shell.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring13.converter.BookToStringConverter;
import ru.korolkovrs.spring13.converter.CommentToStringConverter;
import ru.korolkovrs.spring13.domain.Book;
import ru.korolkovrs.spring13.domain.Comment;
import ru.korolkovrs.spring13.service.BookService;
import ru.korolkovrs.spring13.service.CommentService;
import ru.korolkovrs.spring13.service.IOService;
import ru.korolkovrs.spring13.shell.service.ShellCommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShellCommentServiceIO implements ShellCommentService {
    private final IOService ioService;
    private final CommentService commentService;
    private final BookService bookService;
    private final CommentToStringConverter commentConverter;
    private final BookToStringConverter bookConverter;

    @Override
    public void getCommentById() {
        ioService.out("Введите id комментария:");
        String id = ioService.input();
        Comment comment = commentService.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Комментария с id=%s не найдено", id))
        );
        ioService.out(commentConverter.convert(comment));
    }

    @Override
    public void getAllComment() {
        printAllComments(commentService.findAll());
    }

    @Override
    public void addComment() {
        Book book = getBookByConsole();
        ioService.out("Введите комментарий:");
        String text = ioService.input();
        Comment comment = new Comment();
        comment.setText(text);
        comment.setBook(book);
        commentService.save(comment);
        ioService.out("Комментарий успешно сохранен");
    }

    @Override
    public void updateComment() {
        ioService.out("Введите id комментария, который хотите изменить:");
        String id = ioService.input();
        Comment comment = commentService.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Комментарий с id=%s не найдена", id))
        );
        ioService.out("Введите комментарий:");
        String text = ioService.input();
        comment.setText(text);
        commentService.save(comment);
        ioService.out("Комментарий успешно обновлен");
    }

    @Override
    public void removeComment() {
        ioService.out("Введите id комментария, который хотите удалить:");
        String id = ioService.input();
        commentService.deleteById(id);
    }

    @Override
    public void getCommentByBook() {
        Book book = getBookByConsole();
        List<Comment> comments = commentService.findByBook(book);
        printAllComments(comments);
    }

    private Book getBookByConsole() {
        bookService.findAll().forEach((book -> ioService.out(bookConverter.convert(book))));
        ioService.out("Введите id книги:");
        String id = ioService.input();
        return bookService.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Книга с id=%s не найдена", id))
        );
    }

    private void printAllComments(List<Comment> comments) {
        comments.forEach((comment -> ioService.out(commentConverter.convert(comment))));
    }
}
