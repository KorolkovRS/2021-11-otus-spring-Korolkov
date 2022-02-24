package ru.korolkovrs.spring20.rest.dto.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.korolkovrs.spring20.domain.Comment;
import ru.korolkovrs.spring20.rest.dto.ResponseCommentDto;

@Component
@RequiredArgsConstructor
public class CommentDtoConverter {

    public ResponseCommentDto toResponseCommentDto(Comment comment) {
        ResponseCommentDto commentDto = new ResponseCommentDto(
                comment.getId(),
                comment.getText()
        );
        return commentDto;
    }
}
