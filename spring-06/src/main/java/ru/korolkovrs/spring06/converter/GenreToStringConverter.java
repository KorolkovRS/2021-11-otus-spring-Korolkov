package ru.korolkovrs.spring06.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring06.domain.Genre;

@Component
public class GenreToStringConverter implements Converter<Genre, String> {
    @Override
    public String convert(Genre genre) {
        return String.format("Id: %-4d Жанр: %-15s",
                genre.getId(),
                genre.getGenreName());
    }
}
