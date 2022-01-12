package ru.korolkovrs.spring11.shell.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring11.converter.BookToStringConverter;
import ru.korolkovrs.spring11.converter.CommentToStringConverter;
import ru.korolkovrs.spring11.domain.Author;
import ru.korolkovrs.spring11.domain.Book;
import ru.korolkovrs.spring11.domain.Genre;
import ru.korolkovrs.spring11.service.*;
import ru.korolkovrs.spring11.shell.service.ShellAuthorService;
import ru.korolkovrs.spring11.shell.service.ShellBookService;
import ru.korolkovrs.spring11.shell.service.ShellGenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShellBookServiceIO implements ShellBookService {
    private final BookService bookService;
    private final IOService ioService;
    private final ShellAuthorService shellAuthorService;
    private final AuthorService authorService;
    private final ShellGenreService shellGenreService;
    private final GenreService genreService;
    private final BookToStringConverter bookConverter;
    private final CommentToStringConverter commentConverter;
    private final CommentService commentService;

    private final static String ACCEPT_ANSWER = "y";

    @Override
    public void getAllBook() {
        printBooks(bookService.findAll());
    }

    @Override
    public void getBookById() {
        ioService.out("Введите id книги:");
        Long id = Long.valueOf(ioService.input());
        Book book = bookService.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Книги с id=%d не найдено", id))
        );
        ioService.out(bookConverter.convert(book));
        ioService.out("Показать комментарии к книге? Y/N:");
        String answer = ioService.input().toLowerCase();
        if (answer.equals(ACCEPT_ANSWER)) {
            commentService.findByBook(book).forEach((c) -> ioService.out(commentConverter.convert(c)));
        }
    }

    @Override
    public void getBookByTitle() {
        ioService.out("Введите название книги:");
        String title = ioService.input();
        List<Book> books = bookService.findByTitle(title);
        if (!books.isEmpty()) {
            printBooks(books);
        } else {
            ioService.out(String.format("Книги не найдено"));
        }
    }

    @Override
    public void getBookByAuthor() {
        Author author = getAuthorByConsole();
        printBooks(bookService.findByAuthor(author));

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
        bookService.save(book);
        ioService.out("Книга успешно обновлена");
    }

    @Override
    public void delete() {
        getAllBook();
        ioService.out("Ведите id книги, которую хотите удалить:");
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
        shellAuthorService.getAllAuthor();
        ioService.out("Выберете id автора книги:");
        Long authorId = Long.valueOf(ioService.input());
        return authorService.findById(authorId).orElseThrow(
                () -> new IllegalArgumentException(String.format("Автор с id=%d отсутсвует", authorId))
        );
    }

    private Genre getGenreByConsole() {
        shellGenreService.getAllGenre();
        ioService.out("Выберете id жанра книги:");
        Long genreId = Long.valueOf(ioService.input());
        return genreService.findById(genreId).orElseThrow(
                () -> new IllegalArgumentException(String.format("Жанр с id=%d отсутсвует", genreId))
        );
    }

    private void printBooks(List<Book> books) {
        books.forEach((b) -> ioService.out(bookConverter.convert(b)));
    }
}
