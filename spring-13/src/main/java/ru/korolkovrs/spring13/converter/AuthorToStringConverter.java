package ru.korolkovrs.spring13.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring13.domain.Author;

@Component
public class AuthorToStringConverter implements Converter<Author, String> {

    @Override
    public String convert(Author author) {
        return String.format("Id: %s Автор: %-20s",
                author.getId(),
                author.getName());
    }
}
