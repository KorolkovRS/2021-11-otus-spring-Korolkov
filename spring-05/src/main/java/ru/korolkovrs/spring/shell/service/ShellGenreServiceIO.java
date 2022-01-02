package ru.korolkovrs.spring.shell.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korolkovrs.spring.converter.GenreToStringConverter;
import ru.korolkovrs.spring.domain.Genre;
import ru.korolkovrs.spring.service.GenreService;
import ru.korolkovrs.spring.service.IOService;

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
        Genre genre = genreService.getById(id);
        ioService.out(converter.convert(genre));
    }

    @Override
    public void getAllGenre() {
        genreService.getAll().forEach((g) -> ioService.out(converter.convert(g)));
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
}
