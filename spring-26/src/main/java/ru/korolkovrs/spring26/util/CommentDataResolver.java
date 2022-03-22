package ru.korolkovrs.spring26.util;

import org.springframework.stereotype.Component;
import ru.korolkovrs.spring26.domain.Comment;

import java.util.Date;

@Component
public class CommentDataResolver {
    public Comment addOrUpdateDate(Comment comment) {
        Date date = new Date();
        if (comment.getCreatedAt() == null) {
            comment.setCreatedAt(date);
            comment.setUpdatedAt(date);
            return comment;
        }
        comment.setUpdatedAt(date);
        return comment;
    }
}
