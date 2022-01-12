package ru.korolkovrs.spring11.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring11.domain.Author;

@Component
public class AuthorToStringConverter implements Converter<Author, String> {

    @Override
    public String convert(Author author) {
        return String.format("Id: %-4d Автор: %-20s",
                author.getId(),
                author.getName());
    }
}
