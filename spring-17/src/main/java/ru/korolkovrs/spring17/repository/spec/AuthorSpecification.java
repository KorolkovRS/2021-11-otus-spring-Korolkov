package ru.korolkovrs.spring17.repository.spec;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import ru.korolkovrs.spring17.domain.Author;

@UtilityClass
public class AuthorSpecification {
    public static Specification<Author> likeName(String name) {
        if (name == null) {
            return null;
        }
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%"));
    }
}
