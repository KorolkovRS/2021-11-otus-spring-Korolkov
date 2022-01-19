package ru.korolkovrs.spring13.shell.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring13.converter.AuthorToStringConverter;
import ru.korolkovrs.spring13.domain.Author;
import ru.korolkovrs.spring13.service.AuthorService;
import ru.korolkovrs.spring13.service.IOService;
import ru.korolkovrs.spring13.shell.service.ShellAuthorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShellAuthorServiceIO implements ShellAuthorService {
    private final AuthorService authorService;
    private final IOService ioService;
    private final AuthorToStringConverter converter;

    @Override
    public void getAllAuthor() {
        printAuthors(authorService.findAll());
    }

    @Override
    public void getAuthorById() {
        ioService.out("Введите id автора:");
        String id = ioService.input();
        Author author = authorService.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Автор с id=%s отсутсвует", id))
        );
        ioService.out(converter.convert(author));
    }

    @Override
    public void getAuthorByName() {
        ioService.out("Введите имя автора:");
        String name = ioService.input();
        List<Author> authors = authorService.findByName(name);
        if (authors.isEmpty()) {
            throw new IllegalArgumentException(String.format("Автор с именем '%s' отсутсвует", name));
        }
        printAuthors(authors);
    }

    @Override
    public void saveAuthor() {
        ioService.out("Введите имя автора:");
        String name = ioService.input();
        Author author = new Author();
        author.setName(name);
        authorService.save(author);
        ioService.out("Автор успешно сохранен");
    }

    private void printAuthors(List<Author> authors) {
        authors.forEach((a) -> ioService.out(converter.convert(a)));
    }
}
