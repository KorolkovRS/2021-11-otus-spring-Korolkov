package ru.korolkovrs.spring26.util;

import org.springframework.stereotype.Component;
import ru.korolkovrs.spring26.domain.jpa.CommentJpa;

import java.util.Date;

@Component
public class CommentDataResolver {
    public CommentJpa addOrUpdateDate(CommentJpa commentJpa) {
        Date date = new Date();
        if (commentJpa.getCreatedAt() == null) {
            commentJpa.setCreatedAt(date);
            commentJpa.setUpdatedAt(date);
            return commentJpa;
        }
        commentJpa.setUpdatedAt(date);
        return commentJpa;
    }
}
