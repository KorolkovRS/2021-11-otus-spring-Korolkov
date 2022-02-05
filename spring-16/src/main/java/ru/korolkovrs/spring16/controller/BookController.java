package ru.korolkovrs.spring16.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import ru.korolkovrs.spring16.domain.Author;
import ru.korolkovrs.spring16.domain.Comment;
import ru.korolkovrs.spring16.domain.Genre;
import ru.korolkovrs.spring16.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.korolkovrs.spring16.domain.Book;
import ru.korolkovrs.spring16.dto.converter.BookDtoConverter;
import ru.korolkovrs.spring16.exception.NotFoundException;
import ru.korolkovrs.spring16.service.AuthorService;
import ru.korolkovrs.spring16.service.BookService;
import ru.korolkovrs.spring16.service.GenreService;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookDtoConverter bookDtoConverter;

    @GetMapping("/")
    public String index() {
        return "redirect:/book";
    }

    @GetMapping("/book")
    public String getBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/book/{id}")
    public String getBookById(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        model.addAttribute("comment", new Comment());
        return "book";
    }

    @GetMapping("/book/edit")
    public String showEditBookForm(@RequestParam Long id, Model model) {
        Book book = bookService.findById(id).orElseThrow(NotFoundException::new);
        List<Author> authors = authorService.findAll();
        List<Genre> genres = genreService.findAll();
        Map<String, Object> attributes = Map.of("book", book, "authors", authors, "genres", genres);
        model.addAllAttributes(attributes);
        return "edit_book";
    }

    @PostMapping("/book/edit")
    public String saveOrUpdateBook(@Validated @ModelAttribute("book") BookDto bookDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Author> authors = authorService.findAll();
            List<Genre> genres = genreService.findAll();
            if (bookDto.getId() == null) {
                Map<String, Object> attributes = Map.of("book", bookDto, "authors", authors, "genres", genres);
                model.addAllAttributes(attributes);
                return "add_book";
            }
            Book book = bookService.findById(bookDto.getId()).orElseThrow(NotFoundException::new);
            Map<String, Object> attributes = Map.of("book", book, "authors", authors, "genres", genres);
            model.addAllAttributes(attributes);
            return "edit_book";
        }
        Book book = bookDtoConverter.toDomainObject(bookDto);
        bookService.save(book);
        return "redirect:/";
    }

    @DeleteMapping("/book")
    public String removeBook(@RequestParam Long id) {
        bookService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/book/add")
    public String showAddBookForm(Model model){
        List<Author> authors = authorService.findAll();
        List<Genre> genres = genreService.findAll();
        Map<String, Object> attributes = Map.of("book", new Book(), "authors", authors, "genres", genres);
        model.addAllAttributes(attributes);
        return "add_book";
    }
}
