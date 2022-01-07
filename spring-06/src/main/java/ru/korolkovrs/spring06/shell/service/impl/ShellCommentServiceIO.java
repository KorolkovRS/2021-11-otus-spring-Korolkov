package ru.korolkovrs.spring06.shell.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.korolkovrs.spring06.converter.BookToStringConverter;
import ru.korolkovrs.spring06.domain.Book;
import ru.korolkovrs.spring06.domain.Comment;
import ru.korolkovrs.spring06.service.BookService;
import ru.korolkovrs.spring06.service.CommentService;
import ru.korolkovrs.spring06.service.IOService;
import ru.korolkovrs.spring06.shell.service.ShellCommentService;

@Service
@RequiredArgsConstructor
public class ShellCommentServiceIO implements ShellCommentService {
    private final IOService ioService;
    private final CommentService commentService;
    private final BookService bookService;

    @Override
    public void addComment() {
            ioService.out("Введите id книги, которую хотите прокомментировать:");
            Long id = Long.valueOf(ioService.input());
            Book book = bookService.findById(id).orElseThrow(
                    () -> new IllegalArgumentException(String.format("Книга с id=%d не найдена", id))
            );
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
        Long id = Long.valueOf(ioService.input());
        Comment comment = commentService.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Комментарий с id=%d не найдена", id))
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
        Long id = Long.valueOf(ioService.input());
        commentService.deleteById(id);
    }
}
