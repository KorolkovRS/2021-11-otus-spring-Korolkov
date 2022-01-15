package ru.korolkovrs.spring11.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring11.domain.Book;

@Component
public class BookToStringConverter implements Converter<Book, String> {
    @Override
    public String convert(Book book) {
        return String.format("Id: %-4d Название: %-30s Автор: %-20s Жанр: %-15s",
                book.getId(),
                book.getTitle(),
                book.getAuthor().getName(),
                book.getGenre().getGenreName());
    }
}
