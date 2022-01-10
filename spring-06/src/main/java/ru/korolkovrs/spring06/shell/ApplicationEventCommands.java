package ru.korolkovrs.spring06.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.korolkovrs.spring06.shell.service.ShellAuthorService;
import ru.korolkovrs.spring06.shell.service.ShellBookService;
import ru.korolkovrs.spring06.shell.service.ShellCommentService;
import ru.korolkovrs.spring06.shell.service.ShellGenreService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventCommands {
    private final ShellBookService shellBookService;
    private final ShellAuthorService shellAuthorService;
    private final ShellGenreService shellGenreService;
    private final ShellCommentService shellCommentService;

    @ShellMethod(value = "get_all_book", key = {"get_all_book", "gab"})
    public void getAllBook() {
        shellBookService.getAllBook();
    }

    @ShellMethod(value = "get_book_buy_id", key = {"get_book_buy_id", "gbi"})
    public void getBookById() {
        shellBookService.getBookById();
    }

    @ShellMethod(value = "save_book", key = {"save_book", "sb"})
    public void saveBook() {
        shellBookService.saveBook();
    }

    @ShellMethod(value = "update_book", key = {"update_book", "ub"})
    public void updateBook() {
        shellBookService.updateBook();
    }

    @ShellMethod(value = "delete_book", key = {"delete_book", "db"})
    public void deleteBook() {
        shellBookService.delete();
    }

    @ShellMethod(value = "get_book_by_title", key = {"get_book_by_title", "gbt"})
    public void getAuthorByTitle() {
        shellBookService.getBookByTitle();
    }

    @ShellMethod(value = "get_all_author", key = {"get_all_author", "gaa"})
    public void getAllAuthor() {
        shellAuthorService.getAllAuthor();
    }

    @ShellMethod(value = "get_author_buy_id", key = {"get_author_buy_id", "gai"})
    public void getAuthorById() {
        shellAuthorService.getAuthorById();
    }

    @ShellMethod(value = "get_author_buy_name", key = {"get_author_buy_name", "gan"})
    public void getAuthorByName() {
        shellAuthorService.getAuthorByName();
    }

    @ShellMethod(value = "save_author", key = {"save_author", "sa"})
    public void saveAuthor() {
        shellAuthorService.saveAuthor();
    }

    @ShellMethod(value = "get_all_genre", key = {"get_all_genre", "gag"})
    public void getAllGenre() {
        shellGenreService.getAllGenre();
    }

    @ShellMethod(value = "get_genre_buy_id", key = {"get_genre_buy_id", "ggi"})
    public void getGenreById() {
        shellGenreService.getGenreById();
    }

    @ShellMethod(value = "save_genre", key = {"save_genre", "sg"})
    public void saveGenre() {
        shellGenreService.saveGenre();
    }

    @ShellMethod(value = "add_comment", key = {"add_comment", "ac"})
    public void addComment() {
        shellCommentService.addComment();
    }

    @ShellMethod(value = "update_comment", key = {"update_comment", "uc"})
    public void updateComment() {
        shellCommentService.updateComment();
    }

    @ShellMethod(value = "delete_comment", key = {"delete_comment", "dc"})
    public void deleteComment() {
        shellCommentService.removeComment();
    }
}
