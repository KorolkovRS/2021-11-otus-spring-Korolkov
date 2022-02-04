package ru.korolkovrs.spring17.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.korolkovrs.spring17.domain.Comment;
import ru.korolkovrs.spring17.rest.dto.RequestCommentDto;
import ru.korolkovrs.spring17.exception.NotFoundException;
import ru.korolkovrs.spring17.rest.dto.converter.CommentDtoConverter;
import ru.korolkovrs.spring17.service.CommentService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;
    private final CommentDtoConverter commentDtoConverter;

    @PostMapping("/comment/add")
    public String addOrUpdateComment(RequestCommentDto requestCommentDto) {
        Comment comment = commentDtoConverter.toDomainObject(requestCommentDto);
        commentService.save(comment);
        return "redirect:/book?id=" + comment.getBook().getId();
    }

    @PostMapping("/comment/edit")
    public String showEditCommentForm(Long id, Model model) {
        Comment comment = commentService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("comment", comment);
        return "edit_comment";
    }

    @DeleteMapping("/comment")
    public String removeComment(Long id) {
        Long bookId = commentService.findById(id).orElseThrow(NotFoundException::new).getBook().getId();
        commentService.deleteById(id);
        return "redirect:/book?id=" + bookId;
    }
}
