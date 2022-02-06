package ru.korolkovrs.spring17.repository.spec;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import ru.korolkovrs.spring17.domain.Author;
import ru.korolkovrs.spring17.domain.Book;
import ru.korolkovrs.spring17.domain.Genre;
import ru.korolkovrs.spring17.repository.spec.filter.BookFilter;

import java.util.List;

@UtilityClass
public class BookSpecification {
    private static Specification<Book> likeTitle(String title) {
        String searchPattern = "%" + title + "%";
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .like(criteriaBuilder.lower(root.get("title")), searchPattern.toLowerCase());
    }

    private static Specification<Book> authorEquals(List<Author> authors) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("author")).value(authors));
    }

    private static Specification<Book> genreEquals(Genre genre) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("genre"), genre));
    }

    public static Specification<Book> build(BookFilter filter) {
        Specification<Book> specification = Specification.where(null);
        if (filter.getTitle() != null) {
            specification = specification.and(likeTitle(filter.getTitle()));
        }
        if (filter.getAuthors() != null) {
            specification = specification.and(authorEquals(filter.getAuthors()));
        }
        if (filter.getGenre() != null) {
            specification = specification.and(genreEquals(filter.getGenre()));
        }
        return specification;
    }
}
