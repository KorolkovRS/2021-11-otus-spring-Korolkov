package ru.korolkovrs.spring11.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring11.domain.Comment;

import java.text.SimpleDateFormat;

@Component
public class CommentToStringConverter implements Converter<Comment, String> {
    private final static String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    @Override
    public String convert(Comment comment) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Id: %-4d", comment.getId()));
        sb.append(format.format(comment.getCreatedAt()));
        if (!comment.getUpdatedAt().equals(comment.getCreatedAt())) {
            sb.append("(обновлено ");
            sb.append(format.format(comment.getUpdatedAt()));
            sb.append(')');
        }
        sb.append(": ");
        sb.append(comment.getText());
        return sb.toString();
    }
}
