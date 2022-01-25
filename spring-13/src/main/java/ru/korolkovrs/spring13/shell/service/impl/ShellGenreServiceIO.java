package ru.korolkovrs.spring13.shell.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring13.converter.GenreToStringConverter;
import ru.korolkovrs.spring13.domain.Genre;
import ru.korolkovrs.spring13.service.GenreService;
import ru.korolkovrs.spring13.service.IOService;
import ru.korolkovrs.spring13.shell.service.ShellGenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShellGenreServiceIO implements ShellGenreService {
    private final GenreService genreService;
    private final IOService ioService;
    private final GenreToStringConverter converter;

    @Override
    public void getGenreById() {
        ioService.out("Введите id жанра:");
        String id = ioService.input();
        Genre genre = genreService.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Жанр с id=%s отсутствует", id))
        );
        ioService.out(converter.convert(genre));
    }

    @Override
    public void getAllGenre() {
        printGenres(genreService.findAll());
    }

    @Override
    public void saveGenre() {
        ioService.out("Введите имя жанра:");
        String genreName = ioService.input();
        Genre genre = new Genre();
        genre.setGenreName(genreName);
        genreService.save(genre);
        ioService.out("Жанр успешно сохранен");
    }

    private void printGenres(List<Genre> genres) {
        genres.forEach((g) -> ioService.out(converter.convert(g)));
    }
}
