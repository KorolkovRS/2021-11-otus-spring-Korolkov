package ru.korolkovrs.spring.shell.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring.converter.AuthorToStringConverter;
import ru.korolkovrs.spring.domain.Author;
import ru.korolkovrs.spring.service.AuthorService;
import ru.korolkovrs.spring.service.IOService;

@Service
@RequiredArgsConstructor
public class ShellAuthorServiceIO implements ShellAuthorService {
    private final AuthorService authorService;
    private final IOService ioService;
    private final AuthorToStringConverter converter;

    @Override
    public void getAllAuthor() {
        authorService.getAll().forEach((a) -> ioService.out(converter.convert(a)));
    }

    @Override
    public void getAuthorById() {
        ioService.out("Введите id автора:");
        Long id = Long.valueOf(ioService.input());
        Author author = authorService.getById(id);
        ioService.out(converter.convert(author));
    }

    @Override
    public void getAuthorByName() {
        ioService.out("Введите имя автора:");
        String name = ioService.input();
        Author author = authorService.getByName(name);
        ioService.out(converter.convert(author));
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
}
