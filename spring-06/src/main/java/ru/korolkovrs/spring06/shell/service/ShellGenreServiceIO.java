package ru.korolkovrs.spring06.shell.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring06.converter.GenreToStringConverter;
import ru.korolkovrs.spring06.domain.Genre;
import ru.korolkovrs.spring06.service.GenreService;
import ru.korolkovrs.spring06.service.IOService;

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
        Long id = Long.valueOf(ioService.input());
        Genre genre = genreService.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Жанр с id=%d отсутствует", id))
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
