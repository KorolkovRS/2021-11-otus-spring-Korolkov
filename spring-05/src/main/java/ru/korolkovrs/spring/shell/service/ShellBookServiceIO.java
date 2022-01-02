package ru.korolkovrs.spring.shell.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring.converter.BookToStringConverter;
import ru.korolkovrs.spring.domain.Author;
import ru.korolkovrs.spring.domain.Book;
import ru.korolkovrs.spring.domain.Genre;
import ru.korolkovrs.spring.service.AuthorService;
import ru.korolkovrs.spring.service.BookService;
import ru.korolkovrs.spring.service.GenreService;
import ru.korolkovrs.spring.service.IOService;

@Service
@RequiredArgsConstructor
public class ShellBookServiceIO implements ShellBookService {
    private final BookService bookService;
    private final IOService ioService;
    private final ShellAuthorService shellAuthorService;
    private final AuthorService authorService;
    private final ShellGenreService shellGenreService;
    private final GenreService genreService;
    private final BookToStringConverter converter;

    @Override
    public void getAllBook() {
        bookService.getAll().forEach((b) -> ioService.out(converter.convert(b)));
    }

    @Override
    public void getBookById() {
        ioService.out("Введите id книги:");
        Long id = Long.valueOf(ioService.input());
        Book book = bookService.getById(id);
        ioService.out(converter.convert(book));
    }

    @Override
    public void saveBook() {
        Book book = new Book();
        book.setTitle(getBookNameByConsole());
        book.setAuthor(getAuthorByConsole());
        book.setGenre(getGenreByConsole());
        bookService.save(book);
        ioService.out("Книга успешно сохранена");
    }

    @Override
    public void updateBook() {
        Book book = new Book();
        book.setId(getBookIdByConsole());
        book.setTitle(getBookNameByConsole());
        book.setAuthor(getAuthorByConsole());
        book.setGenre(getGenreByConsole());
        bookService.update(book);
        ioService.out("Книга успешно обновлена");
    }

    @Override
    public void delete() {
        ioService.out("Ведите id книги, которую хотите удалить:");
        getAllBook();
        Long id = Long.valueOf(ioService.input());
        bookService.deleteById(id);
        ioService.out("Книга успешно удалена");
    }

    private Long getBookIdByConsole() {
        ioService.out("Введите id книги:");
        return Long.valueOf(ioService.input());
    }

    private String getBookNameByConsole() {
        ioService.out("Введите название книги:");
        return ioService.input();
    }

    private Author getAuthorByConsole() {
        ioService.out("Выберете id автора книги:");
        shellAuthorService.getAllAuthor();
        Long authorId = Long.valueOf(ioService.input());
        return authorService.getById(authorId);
    }

    private Genre getGenreByConsole() {
        ioService.out("Выберете id жанра книги:");
        shellGenreService.getAllGenre();

        Long genreId = Long.valueOf(ioService.input());
        return genreService.getById(genreId);
    }
}
